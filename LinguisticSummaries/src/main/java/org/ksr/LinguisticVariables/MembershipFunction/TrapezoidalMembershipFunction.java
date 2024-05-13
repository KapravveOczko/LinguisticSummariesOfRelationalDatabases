package org.ksr.LinguisticVariables.MembershipFunction;

public class TrapezoidalMembershipFunction implements MembershipFunction {
    private final String name;
    private final double a, b, c, d;

    public TrapezoidalMembershipFunction(String name, double a, double b, double c, double d) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double calculateMembership(double x) {
        if (x >= a && x < b) {
            return (x - a) / (b - a);
        } else if (x >= b && x <= c) {
            return 1.0;
        } else if (x > c && x <= d) {
            return (d - x) / (d - c);
        } else {
            return 0.0;
        }
    }
}