package com.example.demo.controller;

import com.example.demo.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Tag(name = "Dispatch API", description = "Verwaltung von Sendungen")
@RestController
@RequestMapping("/dispatch")
@Validated
public class DispatchController {

	@Operation(
			summary = "Sendung erstellen",
			description = "Erstellt eine neue Sendung inkl. Verordnungen, Patient und Positionen",
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					required = true,
					content = @Content(
							mediaType = "application/json",
							examples = {

									@ExampleObject(
											name = "Minimal Request",
											summary = "Nur Pflichtfelder",
											value = """
                                            {
                                              "customerNumber": 10001,
                                              "prescriptions": []
                                            }
                                            """
									),

									@ExampleObject(
											name = "Standard Request",
											summary = "Typischer Praxisfall",
											value = """
                                            {
                                              "customerNumber": 10002,
                                              "prescriptions": [
                                                {
                                                  "documentId": "RX-002",
                                                  "issueDate": "2024-05-10",
                                                  "costUnitName": "TK",
                                                  "costUnitIKNumber": 987654321,
                                                  "establishmentId": "111111111",
                                                  "contractMedicalPractitionerID": "222222222",
                                                  "patient": {
                                                    "firstName": "Anna",
                                                    "lastName": "Schmidt"
                                                  },
                                                  "positions": [
                                                    {
                                                      "itemNumber": "P1",
                                                      "itemText": "Paracetamol",
                                                      "singlePrice": 5.50,
                                                      "amount": 2
                                                    }
                                                  ]
                                                }
                                              ]
                                            }
                                            """
									),

									@ExampleObject(
											name = "Allet wat geht",
											summary = "Komplexer Fall",
											value = """
                                            {
                                              "customerNumber": 10003,
                                              "prescriptions": [
                                                {
                                                  "documentId": "RX-003",
                                                  "issueDate": "2024-06-01",
                                                  "costUnitName": "Barmer",
                                                  "costUnitIKNumber": 555555555,
                                                  "establishmentId": "999999999",
                                                  "contractMedicalPractitionerID": "888888888",
                                                  "patient": {
                                                    "firstName": "Tom",
                                                    "lastName": "Weber"
                                                  },
                                                  "positions": [
                                                    {
                                                      "itemNumber": "P1",
                                                      "itemText": "Medikament A",
                                                      "singlePrice": 10.00,
                                                      "amount": 3
                                                    }
                                                  ]
                                                }
                                              ]
                                            }
                                            """
									)
							}
					)
			)
	)
	@ApiResponse(
			responseCode = "200",
			description = "Sendung erfolgreich erstellt",
			content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(
							name = "Success",
							value = """
                            {
                              "dispatchId": "DISP-123456"
                            }
                            """
					)
			)
	)
	@ApiResponse(responseCode = "400", description = "Validierungsfehler")
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<DispatchCreatedResponseDto> createDispatch(
			@Valid @RequestBody DispatchRequestDto request) {

		return ResponseEntity.ok(
				new DispatchCreatedResponseDto("DISP-123456")
		);
	}

	@Operation(
			summary = "Sendungs Status abrufen",
			description = "Gibt den aktuellen Status der Sendung zurück"
	)
	@ApiResponse(
			responseCode = "200",
			description = "Status erfolgreich zurückgegeben",
			content = @Content(
					mediaType = "application/json",
					examples = @ExampleObject(
							value = """
                            {
                              "dispatchId": "DISP-123456",
                              "customerNumber": 10001,
                              "accountSum": 49.99,
                              "status": "IN_PROGRESS"
                            }
                            """
					)
			)
	)
	@GetMapping("/{number}")
	public ResponseEntity<DispatchStatusResponseDto> getStatus(

			@Parameter(
					description = "Dispatch-ID",
					example = "DISP-123456"
			)
			@PathVariable
			@Pattern(regexp = "^[A-Z0-9-]+$")
			String number
	) {

		return ResponseEntity.ok(
				new DispatchStatusResponseDto(
						number,
						10001,
						new BigDecimal("49.99"),
						"IN_PROGRESS"
				)
		);
	}

	@Operation(
			summary = "Sendung stornieren",
			description = "Storniert einen bestehende Sendung"
	)
	@ApiResponse(
			responseCode = "204",
			description = "Erfolgreich gelöscht"
	)
	@DeleteMapping("/{number}")
	public ResponseEntity<Void> cancelDispatch(

			@Parameter(
					description = "Dispatch-ID",
					example = "DISP-123456"
			)
			@PathVariable
			@Pattern(regexp = "^[A-Z0-9-]+$")
			String number
	) {
		return ResponseEntity.noContent().build();
	}
}