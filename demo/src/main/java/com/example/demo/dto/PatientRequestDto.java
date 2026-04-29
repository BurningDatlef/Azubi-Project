package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public record PatientRequestDto(

		@NotBlank
		@Size(max = 256)
		@Schema(description = "Nachname des Patienten", example = "Müller")
		String lastname,

		@NotBlank
		@Size(max = 256)
		@Schema(description = "Vorname des Patienten", example = "Max")
		String firstname,

		@NotNull
		@Schema(description = "Geburtsdatum", example = "1985-10-12")
		LocalDate birthday,

		@NotBlank
		@Size(max = 12)
		@Schema(description = "Versichertennummer", example = "A12345678901")
		String insurantNumber,

		@NotBlank
		@Size(max = 5)
		@Schema(description = "Versichertenstatus", example = "GKV")
		String insurantState,

		@NotBlank
		@Size(max = 256)
		@Schema(example = "Musterstraße 1")
		String street,

		@NotBlank
		@Size(max = 10)
		@Schema(example = "45127")
		String postcode,

		@NotBlank
		@Size(max = 256)
		@Schema(example = "Essen")
		String place,

		@NotBlank
		@Size(min = 3, max = 3)
		@Schema(example = "DEU")
		String countrycode
) {
}