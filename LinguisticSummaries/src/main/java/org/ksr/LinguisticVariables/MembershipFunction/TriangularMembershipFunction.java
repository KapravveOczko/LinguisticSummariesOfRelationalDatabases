package org.ksr.LinguisticVariables.MembershipFunction;

public class TriangularMembershipFunction implements MembershipFunction{
    private final String name;
    private final double a, b, c;

    public TriangularMembershipFunction(String name, double a, double b, double c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculateMembership(double x) {
        if (x >= a && x <= b) {
            return (x - a) / (b - a);
        } else if (x > b && x <= c) {
            return (c - x) / (c - b);
        } else {
            return 0.0;
        }
    }
}

