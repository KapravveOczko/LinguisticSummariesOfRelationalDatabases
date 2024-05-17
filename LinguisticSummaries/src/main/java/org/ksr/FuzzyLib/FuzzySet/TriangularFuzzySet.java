package org.ksr.FuzzyLib.FuzzySet;

public class TriangularFuzzySet extends FuzzySet implements FuzzySetInterface {
    private final String name;
    private final double a, b, c;

    public TriangularFuzzySet(String name, double a, double b, double c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String getName() {
        return this.name;
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

