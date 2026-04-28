package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record PrescriptionRequestDto(

		@NotBlank(message = "Belegnummer fehlt")
		@Size(max = 256, message = "Belegnummer darf maximal 256 Zeichen lang sein")
		String documentId,

		@NotNull(message = "Ausstellungsdatum fehlt")
		LocalDate issueDate,

		@NotBlank(message = "Kostenträger-Name fehlt")
		@Size(max = 256, message = "Kostenträger-Name darf maximal 256 Zeichen lang sein")
		String costUnitName,

		@NotNull(message = "Kostenträger-IK fehlt")
		Integer costUnitIKNumber,

		@NotBlank(message = "Betriebsstättennummer fehlt")
		@Size(max = 9, message = "Betriebsstättennummer darf maximal 9 Zeichen lang sein")
		String establishmentId,

		@NotBlank(message = "Vertragsarztnummer fehlt")
		@Size(max = 9, message = "Vertragsarztnummer darf maximal 9 Zeichen lang sein")
		String contractMedicalPractitionerID,

		@NotNull(message = "Patient fehlt")
		@Valid
		PatientRequestDto patient,

		@NotEmpty(message = "Mindestens eine Position muss geliefert werden")
		List<@Valid PositionRequestDto> positions
) {
}