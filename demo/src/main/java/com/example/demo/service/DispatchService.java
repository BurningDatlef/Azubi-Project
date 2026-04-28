package com.example.demo.service;

import com.example.demo.repo.DispatchRepository;
import com.example.demo.entity.Dispatch;
import com.example.demo.entity.Prescription;
import org.springframework.stereotype.Service;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import java.time.LocalDateTime;

import java.math.BigDecimal;
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
		validate(request);

		Dispatch dispatch = mapToEntity(request);

		dispatch.setDispatchId(UUID.randomUUID().toString());
		dispatch.setStatus("eingereicht");

		calculateSum(dispatch);

		repository.save(dispatch);

		return dispatch.getDispatchId();
	}

	public DispatchStatusResponseDto statusDto(String dispatchId) {
		Dispatch dispatch = status(dispatchId);

		return new DispatchStatusResponseDto(
				dispatch.getDispatchId(),
				dispatch.getCustomerNumber(),
				dispatch.getAccountSum(),
				dispatch.getStatus()
		);
	}

	public Dispatch status(String dispatchId) {
		return repository.findByDispatchId(dispatchId)
						 .orElseThrow();
	}

	public void cancellation(String dispatchId) {
		Dispatch dispatch = status(dispatchId);

		if ("ist abgerechnet".equals(dispatch.getStatus())) {
			throw new RuntimeException("Nicht stornierbar");
		}

		dispatch.setStatus("storniert");
		repository.save(dispatch);
	}

	private void calculateSum(Dispatch dispatch) {
		BigDecimal sum = dispatch.getPrescription().stream()
				.flatMap(v -> v.getPositionen().stream())
				.map(p -> p.getSinglePrice().multiply(BigDecimal.valueOf(p.getAmount())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		dispatch.setAccountSum(sum);
	}

	private void validate(DispatchRequestDto request) {
		List<String> errors = new ArrayList<>();

		if (request.customerNumber() == null) {
			errors.add("Kundennummer fehlt");
		}

		if (request.prescriptions() == null || request.prescriptions().isEmpty()) {
			errors.add("Mindestens eine Verordnung muss geliefert werden");
		} else {
			for (PrescriptionRequestDto prescription : request.prescriptions()) {

				if (isBlank(prescription.documentId())) {
					errors.add("Belegnummer fehlt");
				} else if (prescription.documentId().length() > 256) {
					errors.add("Belegnummer darf maximal 256 Zeichen lang sein");
				}

				if (prescription.issueDate() == null) {
					errors.add("Ausstellungsdatum fehlt");
				}

				if (prescription.patient() == null) {
					errors.add("Patient fehlt");
				} else {
					PatientRequestDto patient = prescription.patient();

					if (prescription.issueDate() != null && patient.birthday() != null) {
						if (prescription.issueDate().isBefore(patient.birthday())) {
							errors.add("Ausstellungsdatum liegt vor Geburtsdatum");
						}
					}

					checkLength(errors, patient.lastname(), 256, "Nachname");
					checkLength(errors, patient.firstname(), 256, "Vorname");
					checkLength(errors, patient.insurantNumber(), 12, "Versichertennummer");
					checkLength(errors, patient.insurantState(), 5, "Versichertenstatus");
					checkLength(errors, patient.street(), 256, "Straße");
					checkLength(errors, patient.postcode(), 10, "Postleitzahl");
					checkLength(errors, patient.place(), 256, "Ort");
					checkLength(errors, patient.countrycode(), 3, "Länderkennzeichen");
				}

				checkLength(errors, prescription.costUnitName(), 256, "Kostenträger-Name");
				checkLength(errors, prescription.establishmentId(), 9, "Betriebsstättennummer");
				checkLength(errors, prescription.contractMedicalPractitionerID(), 9, "Vertragsarztnummer");

				if (request.customerNumber() != null && !isBlank(prescription.documentId())) {
					boolean duplicate = repository.existsByCustomerNumberAndDocumentId(
							request.customerNumber(),
							prescription.documentId()
					);

					if (duplicate) {
						errors.add("Belegnummer wurde für diesen Kunden bereits eingereicht: " + prescription.documentId());
					}
				}

				if (prescription.positions() == null || prescription.positions().isEmpty()) {
					errors.add("Mindestens eine Position muss geliefert werden");
				} else {
					for (PositionRequestDto position : prescription.positions()) {
						checkLength(errors, position.itemNumber(), 10, "Positionsnummer");
						checkLength(errors, position.itemText(), 256, "Positionstext");

						if (position.singlePrice() == null) {
							errors.add("Einzelpreis fehlt");
						}

						if (position.amount() == null) {
							errors.add("Menge fehlt");
						}
					}
				}
			}
		}

		if (!errors.isEmpty()) {
			throw new RuntimeException(errors.toString());
		}
	}

	private void checkLength(List<String> errors, String value, int maxLength, String fieldName) {
		if (value == null || value.isBlank()) {
			errors.add(fieldName + " fehlt");
		} else if (value.length() > maxLength) {
			errors.add(fieldName + " darf maximal " + maxLength + " Zeichen lang sein");
		}
	}

	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}

	private Dispatch mapToEntity(DispatchRequestDto request) {
		Dispatch dispatch = new Dispatch();
		dispatch.setCustomerNumber(request.customerNumber());

		List<Prescription> prescriptions = request.prescriptions().stream().map(prescriptionDto -> {
			Prescription prescription = new Prescription();

			prescription.setDocumentId(prescriptionDto.documentId());
			prescription.setIssueDate(prescriptionDto.issueDate());
			prescription.setCostUnitName(prescriptionDto.costUnitName());
			prescription.setCostUnitIKNumber(prescriptionDto.costUnitIKNumber());
			prescription.setEstablishmentId(prescriptionDto.establishmentId());
			prescription.setContractMedicalPractitionerID(prescriptionDto.contractMedicalPractitionerID());

			PatientRequestDto patientDto = prescriptionDto.patient();

			Patient patient = new Patient();
			patient.setLastname(patientDto.lastname());
			patient.setFirstname(patientDto.firstname());
			patient.setBirthday(patientDto.birthday());
			patient.setInsurantNumber(patientDto.insurantNumber());
			patient.setInsurantState(patientDto.insurantState());
			patient.setStreet(patientDto.street());
			patient.setPostcode(patientDto.postcode());
			patient.setPlace(patientDto.place());
			patient.setCountrycode(patientDto.countrycode());

			prescription.setPatient(patient);
			prescription.setDispatch(dispatch);

			List<Position> positions = prescriptionDto.positions().stream().map(positionDto -> {
				Position position = new Position();

				position.setItemNumber(positionDto.itemNumber());
				position.setItemText(positionDto.itemText());
				position.setSinglePrice(positionDto.singlePrice());
				position.setAmount(positionDto.amount());
				position.setPrescription(prescription);

				return position;
			}).toList();

			prescription.setPositionen(positions);

			return prescription;
		}).toList();

		dispatch.setPrescription(prescriptions);

		return dispatch;
	}
}
