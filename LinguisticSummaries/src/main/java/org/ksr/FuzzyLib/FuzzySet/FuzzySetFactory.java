package org.ksr.FuzzyLib.FuzzySet;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.*;

public class FuzzySetFactory {
    public static FuzzySet createMembershipFunction(int membershipType, String name, Double... params) {
        return switch (membershipType) {
            case TRIANGULAR ->
                    new TriangularFuzzySet(name, params[0], params[1], params[2]);
            case TRAPEZOIDAL ->
                    new TrapezoidalFuzzySet(name, params[0], params[1], params[2], params[3]);
            case GAUSSIAN -> new GaussianFuzzySet(name, params[0], params[1]);
            default -> throw new IllegalArgumentException("unknown case: " + membershipType);
        };
    }
}