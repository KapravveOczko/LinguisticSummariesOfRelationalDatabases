package org.ksr.FuzzyLib.FuzzySet;

import java.util.ArrayList;
import java.util.List;


public class TrapezoidalFuzzySet extends FuzzySet {
    private final String name;
    private final double a, b, c, d;

    public TrapezoidalFuzzySet(String name, double a, double b, double c, double d) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        calculateConcave();
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
    public void calculateSupport(List<Double> data) {
        List<Double> support = new ArrayList<>();
        for(Double value : data){
            if(value >= a || value <= d){
                support.add(value);
            }
        }

        setSupport(support);
    }

    //
    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }
    public double getD() {
        return d;
    }
}