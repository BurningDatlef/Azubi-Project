package com.example.demo;

import com.example.demo.entity.Sendung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SendungRepository extends JpaRepository<Sendung, Long> {
	Optional<Sendung> findBySendungsnummer(String sendungsnummer);
}
