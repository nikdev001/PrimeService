package com.example.PrimeService;


import com.example.PrimeService.services.AlgoTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PrimeServiceApplicationTests {

	@Autowired
	private PrimeService primeService;

	@Test
	public void testCalculatePrimesUsingBruteForce() {
		assertEquals(Arrays.asList(2, 3, 5, 7), primeService.calculatePrimes(10, AlgoTypes.BRUTE_FORCE));
	}

	@Test
	public void testCalculatePrimesUsingHeuristic() {
		assertEquals(Arrays.asList(2, 3, 5, 7), primeService.calculatePrimes(10, AlgoTypes.HEURISTIC));
	}

	@Test
	public void testCalculatePrimesUsingSieve() {
		assertEquals(Arrays.asList(2, 3, 5, 7), primeService.calculatePrimes(10, AlgoTypes.SIEVEOFERATOSTHENES));
	}
}
