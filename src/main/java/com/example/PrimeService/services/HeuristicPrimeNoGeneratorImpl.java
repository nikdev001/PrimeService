package com.example.PrimeService.services;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;

public class HeuristicPrimeNoGeneratorImpl implements IPrimeGenerator {
    private static class HeuristicPrimeNoGeneratorPredicate implements IntPredicate {
        @Override
        public boolean test(int number) {
            if (number == 1 || number == 2) return true;
            if (number % 2 == 0) return false;
            for (int i = 3; i * i <= number; i += 2) {
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
                .filter(new HeuristicPrimeNoGeneratorImpl.HeuristicPrimeNoGeneratorPredicate())
                .boxed()
                .collect(Collectors.toList());
    }
}
