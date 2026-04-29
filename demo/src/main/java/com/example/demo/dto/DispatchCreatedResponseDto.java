package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DispatchCreatedResponseDto(

		@Schema(description = "Eindeutige Dispatch-ID", example = "DISP-2024-0001")
		String dispatchId
) {
}