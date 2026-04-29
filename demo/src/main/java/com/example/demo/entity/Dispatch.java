package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

	@Enumerated(EnumType.STRING)
	private DispatchStatus status;

	private LocalDateTime submittedAt;

	@OneToMany(mappedBy = "dispatch", cascade = CascadeType.ALL)
	private List<Prescription> prescription;
}