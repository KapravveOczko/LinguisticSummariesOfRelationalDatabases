package org.ksr.FuzzyLib.FuzzySet;

import java.util.ArrayList;
import java.util.List;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.GAUSSIAN_STEP;

public class GaussianFuzzySet extends FuzzySet{
    private final String name;
    private final double a, b;

    public GaussianFuzzySet(String name, double a, double b) {
        this.name = name;
        this.a = a; //mean
        this.b = b; //stdDeviation
        calculateConcave();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double calculateMembership(double x) {
        return Math.exp(-0.5 * Math.pow((x - a) / b, 2));
    }

    @Override
    public void calculateSupport(List<Double> data) {
        double start = getBoundaries();
        double stop = start + this.a;
        List<Double> support = new ArrayList<>();

        for(Double value : data){
            if(value >= start && value <= stop){
                support.add(value);
            }
        }

        setSupport(support);
    }

    public double getBoundaries(){
        boolean test = true;
        double boundaries = this.a;
        while(test){
            if(calculateMembership(boundaries) != 0){
                boundaries = boundaries - GAUSSIAN_STEP;
            }
            else {
                test = false;
            }
        }
        return boundaries;
    }

    //
    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }

}