package com.example.demo.scheduler;

import com.example.demo.entity.Dispatch;
import com.example.demo.entity.DispatchStatus;
import com.example.demo.repo.DispatchRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AccountScheduler {

	private final DispatchRepository repo;

	public AccountScheduler(DispatchRepository repo) {
		this.repo = repo;
	}

	@Scheduled(fixedDelay = 5000)
	public void abrechnen() {

		LocalDateTime cutoff = LocalDateTime.now().minusSeconds(30);

		List<Dispatch> dispatches =
				repo.findByStatusAndSubmittedAtBefore(
						DispatchStatus.SUBMITTED,
						cutoff
				);

		dispatches.forEach(d -> {
			d.setStatus(DispatchStatus.INVOICED);
			repo.save(d);
		});
	}
}