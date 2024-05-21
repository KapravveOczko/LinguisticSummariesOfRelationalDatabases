package org.ksr.FuzzyLib.FuzzySet;

import java.util.ArrayList;
import java.util.List;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.STEP;

public class TrapezoidalFuzzySet extends FuzzySet {
    private final String name;
    private final double a, b, c, d;

    public TrapezoidalFuzzySet(String name, double a, double b, double c, double d) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double calculateMembership(double x) {
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

    @Override
    public void calculateSupport() {
        double step = (this.d - this.a) / STEP;
        List<Double> support = new ArrayList<>();

        for (double value = this.a; value <= this.d; value += step) {
            support.add(value);
        }

        setSupport(support);
    }
}