package com.example.demo.dto;

import java.time.LocalDate;

public record PatientRequestDto(
		String lastname,
		String firstname,
		LocalDate birthday,
		String insurantNumber,
		String insurantState,
		String street,
		String postcode,
		String place,
		String countrycode
) {
}