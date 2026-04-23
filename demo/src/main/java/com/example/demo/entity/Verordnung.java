package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
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
}
