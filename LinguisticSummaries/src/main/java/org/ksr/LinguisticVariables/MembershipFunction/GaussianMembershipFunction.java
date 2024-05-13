package org.ksr.LinguisticVariables.MembershipFunction;

public class GaussianMembershipFunction implements MembershipFunction {
    private final String name;
    private final double mean, stdDeviation;

    public GaussianMembershipFunction(String name, double mean, double stdDeviation) {
        this.name = name;
        this.mean = mean;
        this.stdDeviation = stdDeviation;
    }

    @Override
    public double calculateMembership(double x) {
        return Math.exp(-0.5 * Math.pow((x - mean) / stdDeviation, 2));
    }
}