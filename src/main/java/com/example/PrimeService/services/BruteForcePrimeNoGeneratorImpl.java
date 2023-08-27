package com.example.PrimeService.services;
import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BruteForcePrimeNoGeneratorImpl implements IPrimeGenerator {
    private static class BruteForcePrimeNumberPredicate implements IntPredicate {
        public boolean test(int number) {
            for (int i = 2; i < number; ++i) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public List<Integer> generate(int upTo) {
        checkArgument(upTo > 1, "Parameter must be greater than 1");
        return IntStream.rangeClosed(2, upTo)
                .parallel()
                .filter(new BruteForcePrimeNumberPredicate())
                .boxed()
                .collect(Collectors.toList());
    }
}
