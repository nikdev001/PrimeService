package com.example.PrimeService.services;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;

public class SieveEratosthenesPrimeNoGeneratorImpl implements IPrimeGenerator {
    private static class SieveEratosthenesPrimeNoGeneratorPredicate implements IntPredicate {
        @Override
        public boolean test(int number) {
            if (number == 1) return false;  // 1 is not prime
            if (number == 2) return true;   // 2 is prime
            if (number % 2 == 0) return false;  // All other even numbers are not primes

            boolean[] sieve = new boolean[number + 1];
            Arrays.fill(sieve, true);

            // 0 and 1 are not prime
            sieve[0] = false;
            sieve[1] = false;

            for (int i = 2; i <= Math.sqrt(number); i++) {
                // Check if number is prime
                if (sieve[i]) {
                    for (int j = i * i; j <= number; j += i) {
                        sieve[j] = false;
                    }
                }
            }

            return sieve[number];
        }
    }

    @Override
    public List<Integer> generate(int upTo) {
        checkArgument(upTo > 1, "Parameter must be greater than 1");
        return IntStream.rangeClosed(2, upTo)
                .parallel()
                .filter(new SieveEratosthenesPrimeNoGeneratorPredicate())
                .boxed()
                .collect(Collectors.toList());
    }
}
