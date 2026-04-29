package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record DispatchStatusResponseDto(

		@Schema(example = "DISP-2024-0001")
		String dispatchId,

		@Schema(example = "10001")
		Integer customerNumber,

		@Schema(example = "49.99")
		BigDecimal accountSum,

		@Schema(example = "OPEN / CLOSED / FAILED")
		String status
) {
}