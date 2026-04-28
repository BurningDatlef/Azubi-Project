package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientRequestDto(

		@NotBlank(message = "Nachname fehlt")
		@Size(max = 256, message = "Nachname darf maximal 256 Zeichen lang sein")
		String lastname,

		@NotBlank(message = "Vorname fehlt")
		@Size(max = 256, message = "Vorname darf maximal 256 Zeichen lang sein")
		String firstname,

		@NotNull(message = "Geburtsdatum fehlt")
		LocalDate birthday,

		@NotBlank(message = "Versichertennummer fehlt")
		@Size(max = 12, message = "Versichertennummer darf maximal 12 Zeichen lang sein")
		String insurantNumber,

		@NotBlank(message = "Versichertenstatus fehlt")
		@Size(max = 5, message = "Versichertenstatus darf maximal 5 Zeichen lang sein")
		String insurantState,

		@NotBlank(message = "Straße fehlt")
		@Size(max = 256, message = "Straße darf maximal 256 Zeichen lang sein")
		String street,

		@NotBlank(message = "Postleitzahl fehlt")
		@Size(max = 10, message = "Postleitzahl darf maximal 10 Zeichen lang sein")
		String postcode,

		@NotBlank(message = "Ort fehlt")
		@Size(max = 256, message = "Ort darf maximal 256 Zeichen lang sein")
		String place,

		@NotBlank(message = "Länderkennzeichen fehlt")
		@Size(min = 3, max = 3, message = "Länderkennzeichen muss genau 3 Zeichen lang sein")
		String countrycode
) {
}