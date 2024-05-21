package org.ksr.FuzzyLib.FuzzySet;

public class FuzzySetFactory {
    public static FuzzySet createMembershipFunction(String membershipType, String name, Double... params) {
        return switch (membershipType) {
            case FuzzySetConstants.TRIANGULAR ->
                    new TriangularFuzzySet(name, params[0], params[1], params[2]);
            case FuzzySetConstants.TRAPEZOIDAL ->
                    new TrapezoidalFuzzySet(name, params[0], params[1], params[2], params[3]);
            case FuzzySetConstants.GAUSSIAN -> new GaussianFuzzySet(name, params[0], params[1]);
            default -> throw new IllegalArgumentException("unknown case: " + membershipType);
        };
    }
}