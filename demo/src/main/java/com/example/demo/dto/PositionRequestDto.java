package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PositionRequestDto(

		@NotBlank(message = "Positionsnummer fehlt")
		@Size(max = 10, message = "Positionsnummer darf maximal 10 Zeichen lang sein")
		String itemNumber,

		@NotBlank(message = "Positionstext fehlt")
		@Size(max = 256, message = "Positionstext darf maximal 256 Zeichen lang sein")
		String itemText,

		@NotNull(message = "Einzelpreis fehlt")
		@DecimalMin(value = "0.00", inclusive = false, message = "Einzelpreis muss größer als 0 sein")
		BigDecimal singlePrice,

		@NotNull(message = "Menge fehlt")
		@Positive(message = "Menge muss größer als 0 sein")
		Integer amount
) {
}