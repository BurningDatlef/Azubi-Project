package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class App {

	private final SendungRepository repo;

	public App(SendungRepository repo) {
		this.repo = repo;
	}

	@Scheduled(fixedDelay = 5000)
	public void abrechnen() {
		repo.findAll().stream()
				.filter(s -> "eingereicht".equals(s.getStatus()))
				.forEach(s -> {
					s.setStatus("ist abgerechnet");
					repo.save(s);
				});
	}
}
