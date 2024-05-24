package org.ksr.FuzzyLib.FuzzySet;

import java.util.ArrayList;
import java.util.List;

public class TriangularFuzzySet extends FuzzySet {
    private final String name;
    private final double a, b, c;

    public TriangularFuzzySet(String name, double a, double b, double c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        calculateConcave();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double calculateMembership(double x) {
        if (x >= a && x <= b) {
            return (x - a) / (b - a);
        } else if (x > b && x <= c) {
            return (c - x) / (c - b);
        } else {
            return 0.0;
        }
    }

    @Override
    public void calculateSupport(List<Double> data) {
        List<Double> support = new ArrayList<>();
        for(Double value : data){
            if(value >= a && value <= c){
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
}

