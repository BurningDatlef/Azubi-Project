package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record PrescriptionRequestDto(

		@NotBlank
		@Size(max = 256)
		@Schema(example = "RX-2024-0001")
		String documentId,

		@NotNull
		@Schema(example = "2024-05-01")
		LocalDate issueDate,

		@NotBlank
		@Size(max = 256)
		@Schema(example = "AOK Rheinland")
		String costUnitName,

		@NotNull
		@Schema(example = "123456789")
		Integer costUnitIKNumber,

		@NotBlank
		@Size(max = 9)
		@Schema(example = "987654321")
		String establishmentId,

		@NotBlank
		@Size(max = 9)
		@Schema(example = "123456789")
		String contractMedicalPractitionerID,

		@NotNull
		@Valid
		@Schema(description = "Patientendaten")
		PatientRequestDto patient,

		@NotEmpty
		@Valid
		@Schema(description = "Leistungspositionen")
		List<PositionRequestDto> positions
) {
}