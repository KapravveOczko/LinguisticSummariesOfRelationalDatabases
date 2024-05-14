package org.ksr.FuzzyLib.MembershipFunction;

public class MembershipFunctionFactory {
    public static MembershipFunction createMembershipFunction(String membershipType, String name, Double... params) {
        return switch (membershipType) {
            case MembershipFactoryConsts.TRIANGULAR ->
                    new TriangularMembershipFunction(name, params[0], params[1], params[2]);
            case MembershipFactoryConsts.TRAPEZOIDAL ->
                    new TrapezoidalMembershipFunction(name, params[0], params[1], params[2], params[3]);
            case MembershipFactoryConsts.GAUSSIAN -> new GaussianMembershipFunction(name, params[0], params[1]);
            default -> throw new IllegalArgumentException("unknown case: " + membershipType);
        };
    }
}