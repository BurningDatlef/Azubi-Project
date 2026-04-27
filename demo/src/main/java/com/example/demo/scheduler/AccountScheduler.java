package com.example.demo.scheduler;

import com.example.demo.repo.DispatchRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountScheduler {

	private final DispatchRepository repo;

	public AccountScheduler(DispatchRepository repo) {
		this.repo = repo;
	}

	//TODO abrechnen anpassen dass nicht immer alle Sendungen durchgefiltert werden, eingrenzen.
//	@Scheduled(fixedDelay = 5000)
//	public void abrechnen() {
//		System.out.println("Scheduler läuft...");
//
//		repo.findByStatus("eingereicht").stream().forEach(s -> {
//			s.setStatus("ist abgerechnet");
//			repo.save(s);
//		});
//	}
}