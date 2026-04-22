package com.example.demo.service;

import com.example.demo.SendungRepository;
import com.example.demo.entity.Sendung;
import com.example.demo.entity.Verordnung;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SendungService {

	private final SendungRepository repo;

	public SendungService(SendungRepository repo) {
		this.repo = repo;
	}

	public String einreichen(Sendung sendung) {

		validate(sendung);

		sendung.setSendungsnummer(UUID.randomUUID().toString());
		sendung.setStatus("eingereicht");

		berechneSumme(sendung);

		repo.save(sendung);

		return sendung.getSendungsnummer();
	}

	public Sendung status(String nummer) {
		return repo.findBySendungsnummer(nummer)
				.orElseThrow();
	}

	public void stornieren(String nummer) {
		Sendung s = status(nummer);

		if ("ist abgerechnet".equals(s.getStatus())) {
			throw new RuntimeException("Nicht stornierbar");
		}

		s.setStatus("storniert");
		repo.save(s);
	}

	private void berechneSumme(Sendung s) {
		BigDecimal summe = s.getVerordnungen().stream()
				.flatMap(v -> v.getPositionen().stream())
				.map(p -> p.getEinzelpreis().multiply(BigDecimal.valueOf(p.getMenge())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		s.setAbrechnungssumme(summe);
	}

	private void validate(Sendung s) {

		List<String> errors = new ArrayList<>();

		for (Verordnung v : s.getVerordnungen()) {

			if (v.getAusstellungsdatum()
					.isBefore(v.getPatient().getGeburtsdatum())) {

				errors.add("Ausstellungsdatum vor Geburtsdatum");
			}
		}

		if (!errors.isEmpty()) {
			throw new RuntimeException(errors.toString());
		}
	}
}
