package com.example.demo.repo;

import com.example.demo.entity.Sendung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SendungRepository extends JpaRepository<Sendung, Long> {
	Optional<Sendung> findBySendungsnummer(String sendungsnummer);

	List<Sendung> findByStatus(String status);
}
