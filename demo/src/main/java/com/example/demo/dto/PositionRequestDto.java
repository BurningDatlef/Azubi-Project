package com.example.demo.dto;

import java.math.BigDecimal;

public record PositionRequestDto(
		String itemNumber,
		String itemText,
		BigDecimal singlePrice,
		Integer amount
) {
}