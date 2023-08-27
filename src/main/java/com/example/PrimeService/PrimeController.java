package com.example.PrimeService;

import com.example.PrimeService.model.ResponseFormatter;
import com.example.PrimeService.services.AlgoTypes;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/primes")
public class PrimeController {

    private static final String ALGORITHMS = AlgoTypes.HEURISTIC + "," + AlgoTypes.BRUTE_FORCE + "," + AlgoTypes.SIEVEOFERATOSTHENES;

    @Autowired
    private PrimeService primeService;

    @GetMapping(value = "/{number}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getPrimes(
            @PathVariable("number")
            @ApiParam(
                    value = "Number up to which the service will generate prime numbers",
                    required = true
            )
            int number,

            @RequestParam(name = "algorithm", defaultValue = AlgoTypes.SIEVEOFERATOSTHENES)
            @ApiParam(
                    value = "Algorithm to use for prime number verification",
                    allowableValues = ALGORITHMS
            ) final String algorithm,

            @RequestParam(name = "format", defaultValue = "json")
            @ApiParam(
                    value = "Response format, can be json, xml, or text",
                    allowableValues = "json,xml,text"
            ) final String format) throws JsonProcessingException {

        try {
            List<Integer> primes = primeService.calculatePrimes(number, algorithm);
            return createResponse(format, number, primes);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    private ResponseEntity<?> createResponse(String formatType, int initial, List<Integer> result) throws JsonProcessingException {
        return switch (formatType) {
            case "json" -> ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(ResponseFormatter.convertToJson(initial, result));
            case "xml" -> ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(ResponseFormatter.convertToXml(initial, result));
            case "text" -> ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(ResponseFormatter.convertToPlainText(initial, result));
            default -> throw new IllegalArgumentException("format type is not supported");
        };
    }
}
