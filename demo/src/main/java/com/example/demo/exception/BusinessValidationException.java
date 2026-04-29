package com.example.demo.exception;

import java.util.List;

public class BusinessValidationException extends RuntimeException {

	private final List<String> errors;

	public BusinessValidationException(List<String> errors) {
		super("Business validation failed");
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}
}