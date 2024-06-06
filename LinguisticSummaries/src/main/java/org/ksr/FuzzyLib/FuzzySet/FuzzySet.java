package org.ksr.FuzzyLib.FuzzySet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    @Override
    public String toString() {
        return getName();
    }

    public Double calculateMembership(double x){
        return null;
    }

    public void calculateConcave() {
//        boolean concaveTest = true;
//        List<Double> support = getSupport();
//        double step = ALPHA_STEP;
//
//        for (int i = 0; i < support.size() - 1; i++) {
//            double x1 = support.get(i);
//            double x2 = support.get(i + 1);
//
//            for (double lambda = 0; lambda <= 1; lambda += step) {
//                double interpolatedPoint = lambda * x1 + (1 - lambda) * x2;
//                double interpolatedMembership = calculateMembership(interpolatedPoint);
//                double minMembership = Math.min(calculateMembership(x1), calculateMembership(x2));
//
//                if (interpolatedMembership < minMembership) {
//                    concaveTest = false;
//                    break;
//                }
//            }
//
//            if (!concaveTest) {
//                break;
//            }
//        }

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

// degree of fuzziness
//    public Double getImprecision() {
//        double entropy = 0.0;
//        for (double mu : getSupport())
//        {
//            mu = calculateMembership(mu);
//            if (mu > 0 && mu < 1) {
//                entropy += -mu * Math.log(mu) / Math.log(2) - (1 - mu) * Math.log(1 - mu) / Math.log(2);
//            }
//        }
//        return entropy;
//
//    }
public Double getImprecision() {

    Double imprecision = 0.0;
    Double cardinality = getCardinality();
    List<Double> support = getSupport();

    for(Double position: support){
        imprecision += Math.pow(calculateMembership(position) / cardinality, 2);
    }

    return 1 - imprecision;

}

    public void setValues(List<Double> data){

        ArrayList<Double> support = new ArrayList<>();
        Double cardinality = 0.0;
        Double x = 0.0;

        for(Double value : data){
            x = calculateMembership(value);
            if(x != 0){
                support.add(value);
                cardinality += x;
            }
        }

        setSupport(support);
        setCardinality(cardinality);

//        System.out.println("calculated support: " + getSupport());
//        System.out.println("calculated support size: " + getSupport().size());
//        System.out.println("calculated cardinality: " + getCardinality());
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
