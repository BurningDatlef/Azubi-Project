package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 256)
	private String documentId;

	private LocalDate issueDate;

	@Column(length = 256)
	private String costUnitName;

	private Integer costUnitIKNumber;

	@Column(length = 9)
	private String establishmentId;

	@Column(length = 9)
	private String contractMedicalPractitionerID;

	@OneToOne(cascade = CascadeType.ALL)
	private Patient patient;

	@OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
	private List<Position> positions;

	@ManyToOne
	@JoinColumn(name = "dispatch_id")
	private Dispatch dispatch;
}
