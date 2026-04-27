package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Sendung {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String sendungsnummer;
	private Integer kundennummer;
	private BigDecimal abrechnungssumme;
	private String status;

	@OneToMany(mappedBy = "sendung", cascade = CascadeType.ALL)
	private List<Verordnung> verordnungen;
}
