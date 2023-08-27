package com.example.PrimeService.services;

public class PrimeAlgorithmFactory {
    public static IPrimeGenerator getPrimeNoGeneratorInstance(String algoType) {

        return switch (algoType) {
            case AlgoTypes.BRUTE_FORCE -> new BruteForcePrimeNoGeneratorImpl();
            case AlgoTypes.HEURISTIC -> new HeuristicPrimeNoGeneratorImpl();
            case AlgoTypes.SIEVEOFERATOSTHENES -> new SieveEratosthenesPrimeNoGeneratorImpl();
            default -> throw new IllegalArgumentException("algoType '" + algoType + "' not found");
        };
    }
}
