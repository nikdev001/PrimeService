package com.example.PrimeService;

import com.example.PrimeService.services.PrimeAlgorithmFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimeService {

    @Cacheable("primes")
    public List<Integer> calculatePrimes(int upTo, String algoTyp) {
        return PrimeAlgorithmFactory.getPrimeNoGeneratorInstance(algoTyp).generate(upTo);
    }
}

