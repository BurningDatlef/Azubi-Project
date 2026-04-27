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
	private String itemNumber;

	@Column(length = 256)
	private String itemText;

	@ManyToOne
	@JoinColumn(name = "prescription_id")
	private Prescription prescription;

	private BigDecimal singlePrice;
	private Integer amount;

}
