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
	private String lastname;

	@Column(length = 256)
	private String firstname;

	private LocalDate birthday;

	@Column(length = 12)
	private String insurantNumber;

	@Column(length = 5)
	private String insurantState;

	@Column(length = 256)
	private String street;

	@Column(length = 10)
	private String postcode;

	@Column(length = 256)
	private String place;

	@Column(length = 3)
	private String countrycode;
}
