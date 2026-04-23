package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AbrechnungScheduler {

	private final SendungRepository repo;

	public AbrechnungScheduler(SendungRepository repo) {
		this.repo = repo;
	}

	@Scheduled(fixedDelay = 5000)
	public void abrechnen() {
		System.out.println("Scheduler läuft...");

		repo.findAll().stream()
				.filter(s -> "eingereicht".equals(s.getStatus()))
				.forEach(s -> {
					s.setStatus("ist abgerechnet");
					repo.save(s);
				});
	}
}