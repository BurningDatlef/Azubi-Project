package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DispatchRequestDto(

		@NotNull(message = "Kundennummer fehlt")
		@Schema(description = "Eindeutige Kundennummer", example = "10001")
		Integer customerNumber,

		@NotEmpty(message = "Mindestens ein Rezept muss vorhanden sein")
		@Valid
		@Schema(description = "Liste der Rezepte")
		List<PrescriptionRequestDto> prescriptions
) {
}