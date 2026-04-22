package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
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

	// Getter & Setter

	public Long getId() {
		return id;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getVersichertennummer() {
		return versichertennummer;
	}

	public void setVersichertennummer(String versichertennummer) {
		this.versichertennummer = versichertennummer;
	}

	public String getVersichertenstatus() {
		return versichertenstatus;
	}

	public void setVersichertenstatus(String versichertenstatus) {
		this.versichertenstatus = versichertenstatus;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getPostleitzahl() {
		return postleitzahl;
	}

	public void setPostleitzahl(String postleitzahl) {
		this.postleitzahl = postleitzahl;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLaenderkennzeichen() {
		return laenderkennzeichen;
	}

	public void setLaenderkennzeichen(String laenderkennzeichen) {
		this.laenderkennzeichen = laenderkennzeichen;
	}
}
