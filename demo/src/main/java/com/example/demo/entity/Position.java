package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 10)
	private String positionsnummer;

	@Column(length = 256)
	private String positionstext;

	@ManyToOne
	@JoinColumn(name = "verordnung_id")
	private Verordnung verordnung;

	private BigDecimal einzelpreis;
	private Integer menge;

}
