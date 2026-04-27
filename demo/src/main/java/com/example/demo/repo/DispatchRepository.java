package com.example.demo.repo;

import com.example.demo.entity.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DispatchRepository extends JpaRepository<Dispatch, Long> {
	Optional<Dispatch> findByDispatchId(String dispatchId);

	List<Dispatch> findByStatus(String status);
}
