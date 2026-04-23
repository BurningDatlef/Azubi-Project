package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 256)
	private String nachname;

	@Column(length = 256)
	private String vorname;

	private LocalDate geburtsdatum;

	@Column(length = 12)
	private String versichertennummer;

	@Column(length = 5)
	private String versichertenstatus;

	@Column(length = 256)
	private String strasse;

	@Column(length = 10)
	private String postleitzahl;

	@Column(length = 256)
	private String ort;

	@Column(length = 3)
	private String laenderkennzeichen;
}
