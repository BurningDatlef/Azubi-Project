package com.example.demo.controller;

import com.example.demo.dto.DispatchCreatedResponseDto;
import com.example.demo.dto.DispatchRequestDto;
import com.example.demo.dto.DispatchStatusResponseDto;
import com.example.demo.service.DispatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {

	private final DispatchService service;

	public DispatchController(DispatchService service) {
		this.service = service;
	}

	@PostMapping
	public DispatchCreatedResponseDto tender(@RequestBody DispatchRequestDto request) {
		String dispatchId = service.tender(request);
		return new DispatchCreatedResponseDto(dispatchId);
	}

	@GetMapping("/{number}")
	public DispatchStatusResponseDto status(@PathVariable String number) {
		return service.statusDto(number);
	}

	@DeleteMapping("/{number}")
	public void cancellation(@PathVariable String number) {
		service.cancellation(number);
	}
}