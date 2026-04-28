package com.example.demo.dto;

import java.util.List;

public record DispatchRequestDto(
		Integer customerNumber,
		List<PrescriptionRequestDto> prescriptions
) {
}