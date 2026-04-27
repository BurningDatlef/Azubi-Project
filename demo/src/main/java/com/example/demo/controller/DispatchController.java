package com.example.demo.controller;

import com.example.demo.entity.Dispatch;
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
	public String tender(@RequestBody Dispatch dispatch) {
		return service.tender(dispatch);
	}

	@GetMapping("/{number}")
	public Dispatch status(@PathVariable String number) {
		return service.status(number);
	}

	@DeleteMapping("/{number}")
	public void cancellation(@PathVariable String number) {
		service.cancellation(number);
	}
}
