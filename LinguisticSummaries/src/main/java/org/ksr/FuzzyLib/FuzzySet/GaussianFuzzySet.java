package org.ksr.FuzzyLib.FuzzySet;

import java.util.ArrayList;
import java.util.List;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.GAUSSIAN_STEP;
import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.STEP;

public class GaussianFuzzySet extends FuzzySet{
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
    public Double calculateMembership(double x) {
        return Math.exp(-0.5 * Math.pow((x - mean) / stdDeviation, 2));
    }

    @Override
    public void calculateSupport() {
        double start = getBoundaries();
        double stop = start + this.mean;
        double step = (stop - start) / STEP;
        List<Double> support = new ArrayList<>();

        for (double value = start; value <= stop; value += step) {
            support.add(value);
        }

        setSupport(support);
    }

    public double getBoundaries(){
        boolean test = true;
        double boundaries = this.mean;
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
}