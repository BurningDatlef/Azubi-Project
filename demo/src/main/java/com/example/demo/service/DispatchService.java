package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.repo.DispatchRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DispatchService {

	private final DispatchRepository repository;

	public DispatchService(DispatchRepository repository) {
		this.repository = repository;
	}

	public String tender(DispatchRequestDto request) {

		validateBusinessRules(request);

		Dispatch dispatch = mapToEntity(request);

		dispatch.setDispatchId(UUID.randomUUID().toString());
		dispatch.setStatus(DispatchStatus.SUBMITTED);
		dispatch.setSubmittedAt(LocalDateTime.now());

		calculateSum(dispatch);

		repository.save(dispatch);

		return dispatch.getDispatchId();
	}

	public DispatchStatusResponseDto statusDto(String dispatchId) {
		Dispatch dispatch = getDispatch(dispatchId);

		return new DispatchStatusResponseDto(
				dispatch.getDispatchId(),
				dispatch.getCustomerNumber(),
				dispatch.getAccountSum(),
				dispatch.getStatus().name()
		);
	}

	public Dispatch getDispatch(String dispatchId) {
		return repository.findByDispatchId(dispatchId)
				.orElseThrow(() -> new RuntimeException("Dispatch nicht gefunden: " + dispatchId));
	}

	public void cancel(String dispatchId) {
		Dispatch dispatch = getDispatch(dispatchId);

		if (dispatch.getStatus() == DispatchStatus.INVOICED) {
			throw new RuntimeException("Dispatch ist bereits abgerechnet und kann nicht storniert werden");
		}

		dispatch.setStatus(DispatchStatus.CANCELLED);
		repository.save(dispatch);
	}

	private void validateBusinessRules(DispatchRequestDto request) {

		List<String> errors = new ArrayList<>();

		if (request.customerNumber() == null) {
			errors.add("Kundennummer fehlt");
		}

		if (request.prescriptions() == null || request.prescriptions().isEmpty()) {
			errors.add("Mindestens eine Verordnung muss vorhanden sein");
			throw new BusinessValidationException(errors);
		}

		for (PrescriptionRequestDto prescription : request.prescriptions()) {

			validatePrescription(prescription, request.customerNumber(), errors);
		}

		if (!errors.isEmpty()) {
			throw new BusinessValidationException(errors);
		}
	}

	private void validatePrescription(
			PrescriptionRequestDto prescription,
			Integer customerNumber,
			List<String> errors
	) {

		if (prescription.issueDate() != null &&
				prescription.patient() != null &&
				prescription.issueDate().isBefore(prescription.patient().birthday())) {
			errors.add("Ausstellungsdatum liegt vor Geburtsdatum");
		}

		if (prescription.issueDate() != null &&
				prescription.issueDate().isAfter(LocalDate.now())) {
			errors.add("Ausstellungsdatum darf nicht in der Zukunft liegen");
		}

		boolean duplicate = repository.existsByCustomerNumberAndDocumentId(
				customerNumber,
				prescription.documentId()
		);

		if (duplicate) {
			errors.add("Belegnummer bereits vorhanden: " + prescription.documentId());
		}
	}

	private void calculateSum(Dispatch dispatch) {

		BigDecimal sum = dispatch.getPrescription().stream()
				.filter(p -> p.getPositions() != null)
				.flatMap(p -> p.getPositions().stream())
				.map(pos ->
						pos.getSinglePrice().multiply(BigDecimal.valueOf(pos.getAmount()))
				)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		dispatch.setAccountSum(sum);
	}

	private Dispatch mapToEntity(DispatchRequestDto request) {

		Dispatch dispatch = new Dispatch();
		dispatch.setCustomerNumber(request.customerNumber());

		List<Prescription> prescriptions = request.prescriptions().stream()
				.map(dto -> {

					Prescription p = new Prescription();
					p.setDocumentId(dto.documentId());
					p.setIssueDate(dto.issueDate());
					p.setCostUnitName(dto.costUnitName());
					p.setCostUnitIKNumber(dto.costUnitIKNumber());
					p.setEstablishmentId(dto.establishmentId());
					p.setContractMedicalPractitionerID(dto.contractMedicalPractitionerID());

					PatientRequestDto pd = dto.patient();

					Patient patient = new Patient();
					patient.setLastname(pd.lastname());
					patient.setFirstname(pd.firstname());
					patient.setBirthday(pd.birthday());
					patient.setInsurantNumber(pd.insurantNumber());
					patient.setInsurantState(pd.insurantState());
					patient.setStreet(pd.street());
					patient.setPostcode(pd.postcode());
					patient.setPlace(pd.place());
					patient.setCountrycode(pd.countrycode());

					p.setPatient(patient);
					p.setDispatch(dispatch);

					List<Position> positions = dto.positions().stream()
							.map(posDto -> {

								Position pos = new Position();
								pos.setItemNumber(posDto.itemNumber());
								pos.setItemText(posDto.itemText());
								pos.setSinglePrice(posDto.singlePrice());
								pos.setAmount(posDto.amount());
								pos.setPrescription(p);

								return pos;
							}).toList();

					p.setPositions(positions);

					return p;
				}).toList();

		dispatch.setPrescription(prescriptions);

		return dispatch;
	}
}