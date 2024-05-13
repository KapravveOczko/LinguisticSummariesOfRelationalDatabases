package org.ksr.LinguisticVariables.MembershipFunction;

import java.util.Map;

public class MembershipFunctionFactory {
    public static MembershipFunction createMembershipFunction(String membershipType, String name, Double... params) {
        switch (membershipType) {
            case MembershipFactoryConsts.TRIANGULAR:
                return new TriangularMembershipFunction(name, params[0], params[1], params[2]);
            case MembershipFactoryConsts.TRAPEZOIDAL:
                return new TrapezoidalMembershipFunction(name, params[0], params[1], params[2], params[3]);
            case MembershipFactoryConsts.GAUSSIAN:
                return new GaussianMembershipFunction(name, params[0], params[1]);
            default:
                throw new IllegalArgumentException("unknown case: " + membershipType);
        }
    }
}

/*
Factory params to actual params:
params[0] = a
params[1] = b
params[2] = c
params[3] = d

NOTE #1:
    there mey be problem on border functions
    while not defining functions bounds we can easily allow all to add their own functions
    however there mey be problem when record has x smaller than "printer bonds" <-- se printer functions

    Double.NEGATIVE_INFINITY i Double.POSITIVE_INFINITY on near bonds

*/
