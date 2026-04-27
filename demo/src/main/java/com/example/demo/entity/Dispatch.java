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

//TODO DTO Klassen angucken etc.
//TODO Endpunkte haben verschiedende rückgaben
@Entity
@Getter
@Setter
public class Dispatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String dispatchId;
	private Integer customerNumber;
	private BigDecimal accountSum;
	private String status;

	@OneToMany(mappedBy = "dispatch", cascade = CascadeType.ALL)
	private List<Prescription> prescription;
}
