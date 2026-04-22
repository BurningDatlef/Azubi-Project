package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 10)
	private String positionsnummer;

	@Column(length = 256)
	private String positionstext;

	private BigDecimal einzelpreis;
	private Integer menge;

	// Getter & Setter

	public Long getId() {
		return id;
	}

	public String getPositionsnummer() {
		return positionsnummer;
	}

	public void setPositionsnummer(String positionsnummer) {
		this.positionsnummer = positionsnummer;
	}

	public String getPositionstext() {
		return positionstext;
	}

	public void setPositionstext(String positionstext) {
		this.positionstext = positionstext;
	}

	public BigDecimal getEinzelpreis() {
		return einzelpreis;
	}

	public void setEinzelpreis(BigDecimal einzelpreis) {
		this.einzelpreis = einzelpreis;
	}

	public Integer getMenge() {
		return menge;
	}

	public void setMenge(Integer menge) {
		this.menge = menge;
	}
}
