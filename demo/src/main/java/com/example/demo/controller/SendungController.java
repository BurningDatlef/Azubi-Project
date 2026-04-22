package com.example.demo.controller;

import com.example.demo.entity.Sendung;
import com.example.demo.service.SendungService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sendungen")
public class SendungController {

	private final SendungService service;

	public SendungController(SendungService service) {
		this.service = service;
	}

	@PostMapping
	public String einreichen(@RequestBody Sendung s) {
		return service.einreichen(s);
	}

	@GetMapping("/{nummer}")
	public Sendung status(@PathVariable String nummer) {
		return service.status(nummer);
	}

	@DeleteMapping("/{nummer}")
	public void stornieren(@PathVariable String nummer) {
		service.stornieren(nummer);
	}
}
