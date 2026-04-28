package com.example.demo.dto;

import java.math.BigDecimal;

public record DispatchStatusResponseDto(
		String dispatchId,
		Integer customerNumber,
		BigDecimal accountSum,
		String status
) {
}