package com.example.demo.service;

import com.example.demo.repo.DispatchRepository;
import com.example.demo.entity.Dispatch;
import com.example.demo.entity.Prescription;
import org.springframework.stereotype.Service;

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

	public String tender(Dispatch dispatch) {
		//anotationen selber schreiben und an sich die ganzen anotation die häufig genutzt werden.
		validate(dispatch);

		dispatch.setDispatchId(UUID.randomUUID().toString());
		dispatch.setStatus("eingereicht");

		calculateSum(dispatch);

		repository.save(dispatch);

		return dispatch.getDispatchId();
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

	private void validate(Dispatch dispatch) {

		List<String> errors = new ArrayList<>();

		for (Prescription prescription : dispatch.getPrescription()) {

			if (prescription.getIssueDate()
					.isBefore(prescription.getPatient().getBirthday())) {

				errors.add("Ausstellungsdatum vor Geburtsdatum");
			}
		}

		if (!errors.isEmpty()) {
			throw new RuntimeException(errors.toString());
		}
	}
}
