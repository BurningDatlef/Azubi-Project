package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessValidationException.class)
	public ResponseEntity<?> handle(BusinessValidationException ex) {
		return ResponseEntity.badRequest().body(ex.getErrors());
	}
}