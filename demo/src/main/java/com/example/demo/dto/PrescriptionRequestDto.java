package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

public record PrescriptionRequestDto(
		String documentId,
		LocalDate issueDate,
		String costUnitName,
		Integer costUnitIKNumber,
		String establishmentId,
		String contractMedicalPractitionerID,
		PatientRequestDto patient,
		List<PositionRequestDto> positions
) {
}