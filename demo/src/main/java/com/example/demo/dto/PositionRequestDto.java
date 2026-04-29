package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public record PositionRequestDto(

		@NotBlank
		@Size(max = 10)
		@Schema(example = "P12345")
		String itemNumber,

		@NotBlank
		@Size(max = 256)
		@Schema(example = "Ibuprofen 600mg")
		String itemText,

		@NotNull
		@DecimalMin(value = "0.00", inclusive = false)
		@Schema(example = "12.99")
		BigDecimal singlePrice,

		@NotNull
		@Positive
		@Schema(example = "2")
		Integer amount
) {
}