package com.example.PrimeService;

import com.example.PrimeService.services.PrimeAlgorithmFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimeService {

    private final Cache primesCache;

    @Autowired
    public PrimeService(CacheManager cacheManager) {
        this.primesCache = cacheManager.getCache("primes");
    }

    @Cacheable(value = "primes", key = "#upTo + '-' + #algoTyp")
    public List<Integer> calculatePrimes(int upTo, String algoTyp) {
        Cache.ValueWrapper cachedValue = primesCache.get(upTo + "-" + algoTyp);

        if (cachedValue != null) {
            return (List<Integer>) cachedValue.get();
        }

        List<Integer> primes = PrimeAlgorithmFactory.getPrimeNoGeneratorInstance(algoTyp).generate(upTo);
        primesCache.put(upTo + "-" + algoTyp, primes);

        return primes;
    }
}