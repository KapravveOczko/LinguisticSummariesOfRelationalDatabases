package org.ksr.FuzzyLib.FuzzySet;

public class GaussianFuzzySet extends FuzzySet implements FuzzySetInterface {
    private final String name;
    private final double mean, stdDeviation;

    public GaussianFuzzySet(String name, double mean, double stdDeviation) {
        this.name = name;
        this.mean = mean;
        this.stdDeviation = stdDeviation;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double calculateMembership(double x) {
        return Math.exp(-0.5 * Math.pow((x - mean) / stdDeviation, 2));
    }
}