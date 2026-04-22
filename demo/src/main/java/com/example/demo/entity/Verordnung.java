package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Verordnung {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 256)
	private String belegnummer;

	private LocalDate ausstellungsdatum;

	@Column(length = 256)
	private String kostentraegerName;

	private Integer kostentraegerIK;

	@Column(length = 9)
	private String betriebsstaettennummer;

	@Column(length = 9)
	private String vertragsarztnummer;

	@OneToOne(cascade = CascadeType.ALL)
	private Patient patient;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Position> positionen;

	// Getter & Setter

	public Long getId() {
		return id;
	}

	public String getBelegnummer() {
		return belegnummer;
	}

	public void setBelegnummer(String belegnummer) {
		this.belegnummer = belegnummer;
	}

	public LocalDate getAusstellungsdatum() {
		return ausstellungsdatum;
	}

	public void setAusstellungsdatum(LocalDate ausstellungsdatum) {
		this.ausstellungsdatum = ausstellungsdatum;
	}

	public String getKostentraegerName() {
		return kostentraegerName;
	}

	public void setKostentraegerName(String kostentraegerName) {
		this.kostentraegerName = kostentraegerName;
	}

	public Integer getKostentraegerIK() {
		return kostentraegerIK;
	}

	public void setKostentraegerIK(Integer kostentraegerIK) {
		this.kostentraegerIK = kostentraegerIK;
	}

	public String getBetriebsstaettennummer() {
		return betriebsstaettennummer;
	}

	public void setBetriebsstaettennummer(String betriebsstaettennummer) {
		this.betriebsstaettennummer = betriebsstaettennummer;
	}

	public String getVertragsarztnummer() {
		return vertragsarztnummer;
	}

	public void setVertragsarztnummer(String vertragsarztnummer) {
		this.vertragsarztnummer = vertragsarztnummer;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Position> getPositionen() {
		return positionen;
	}

	public void setPositionen(List<Position> positionen) {
		this.positionen = positionen;
	}
}
