package org.ksr.FuzzyLib.FuzzySet;

public class FuzzySetFactory {
    public static FuzzySet createMembershipFunction(String membershipType, String name, Double... params) {
        return switch (membershipType) {
            case FuzzySetConsts.TRIANGULAR ->
                    new TriangularFuzzySet(name, params[0], params[1], params[2]);
            case FuzzySetConsts.TRAPEZOIDAL ->
                    new TrapezoidalFuzzySet(name, params[0], params[1], params[2], params[3]);
            case FuzzySetConsts.GAUSSIAN -> new GaussianFuzzySet(name, params[0], params[1]);
            default -> throw new IllegalArgumentException("unknown case: " + membershipType);
        };
    }
}