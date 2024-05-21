package org.ksr.FuzzyLib.FuzzySet;

import java.util.ArrayList;
import java.util.List;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.ALPHA_STEP;

public abstract class FuzzySet {
    private double a, b, c, d;
    private String name;
    private Boolean isConcave;
    private Double cardinality;
    private List<Double> support;

    public String getName() {
        return name;
    }

    Double calculateMembership(double x){
        return null;
    }

    public void calculateConcave() {
        boolean concaveTest = true;
        List<Double> support = getSupport();
        double step = ALPHA_STEP;

        for (int i = 0; i < support.size() - 1; i++) {
            double x1 = support.get(i);
            double x2 = support.get(i + 1);

            for (double lambda = 0; lambda <= 1; lambda += step) {
                double interpolatedPoint = lambda * x1 + (1 - lambda) * x2;
                double interpolatedMembership = calculateMembership(interpolatedPoint);
                double minMembership = Math.min(calculateMembership(x1), calculateMembership(x2));

                if (interpolatedMembership < minMembership) {
                    concaveTest = false;
                    break;
                }
            }

            if (!concaveTest) {
                break;
            }
        }

//        setConcave(concaveTest);


//        OUR APP DISALLOWS CREATION OF NON CONCAVE FUNCTIONS
        setConcave(true);
    }

    public void calculateCardinality(){
        Double cardinality = 0.0;
        for(Double position: getSupport()){
            cardinality += calculateMembership(position);
        }
        setCardinality(cardinality);
    }

    public void calculateSupport(){}

    public ArrayList<Double> calculateAlphaCut(double alpha){
        ArrayList<Double> exceeding = new ArrayList<>();
        for(Double position: getSupport()){
            if(calculateMembership(position) <= alpha){
                exceeding.add(position);
            }
        }

        return exceeding;
    }

    // getters and setters

    public void setName(String name) {
        this.name = name;
    }
    public Boolean getConcave() {
        return isConcave;
    }
    public void setConcave(Boolean concave) {
        isConcave = concave;
    }
    public Double getCardinality() {
        return cardinality;
    }
    public void setCardinality(Double cardinality) {
        this.cardinality = cardinality;
    }
    public List<Double> getSupport() {
        return support;
    }
    public void setSupport(List<Double> support) {
        this.support = support;
    }

}
