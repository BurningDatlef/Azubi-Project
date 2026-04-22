package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Sendung {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String sendungsnummer;
	private Integer kundennummer;
	private BigDecimal abrechnungssumme;
	private String status;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Verordnung> verordnungen;

	// Getter & Setter

	public Long getId() {
		return id;
	}

	public String getSendungsnummer() {
		return sendungsnummer;
	}

	public void setSendungsnummer(String sendungsnummer) {
		this.sendungsnummer = sendungsnummer;
	}

	public Integer getKundennummer() {
		return kundennummer;
	}

	public void setKundennummer(Integer kundennummer) {
		this.kundennummer = kundennummer;
	}

	public BigDecimal getAbrechnungssumme() {
		return abrechnungssumme;
	}

	public void setAbrechnungssumme(BigDecimal abrechnungssumme) {
		this.abrechnungssumme = abrechnungssumme;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Verordnung> getVerordnungen() {
		return verordnungen;
	}

	public void setVerordnungen(List<Verordnung> verordnungen) {
		this.verordnungen = verordnungen;
	}
}
