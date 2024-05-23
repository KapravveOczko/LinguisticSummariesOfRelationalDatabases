package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

public class TruthChecker {
    private static TruthChecker instance;

    private TruthChecker() {
    }

    public static TruthChecker getInstance() {
        if (instance == null) {
            instance = new TruthChecker();
        }
        return instance;
    }

    // Will check degreeOfTruth using average from all truth checking methods
    public Float checkTruth(List<List> data, List<LinguisticVariable> summarizers, Label qualifier, Label quantivier ) {
        float result = 0.0F;


        return result;
    }

    private float T1(List<Double> data, FuzzySet summarizer, boolean isRelativeQuantifier) {
        float result = 0.0F;

        for(Double value : data)
        {
            result+=summarizer.calculateMembership(value);
        }

        if(isRelativeQuantifier){
            return result/data.size();
        }
        else {
            return result;
        }
    }

//    5.24
    public float T2(List<LinguisticVariable> summarizers) {
        double productImprecision = 1.0;
        int p = summarizers.size();

        for (LinguisticVariable summarizer : summarizers) {
            List<FuzzySet> fuzzySets = summarizer.getFuzzySets();
            for (FuzzySet fuzzySet : fuzzySets) {
                productImprecision *= fuzzySet.getImprecision();
            }
        }

        return 1 - (float) Math.pow(productImprecision, p);
    }

    public float T3(List<List<Double>> data, List<LinguisticVariable> summarizers) {
        double sumCoverages = 0.0;
        int dataSize = data.get(0).size();

        for (int index = 0; index < dataSize; index++) {
            double minMembership = Double.MAX_VALUE;
            for (int i = 0; i < summarizers.size(); i++) {
                LinguisticVariable summarizer = summarizers.get(i);
                double value = data.get(i).get(index);
                List<FuzzySet> fuzzySets = summarizer.getFuzzySets();
                for (FuzzySet fuzzySet : fuzzySets) {
                    double membership = fuzzySet.calculateMembership(value);
                    minMembership = Math.min(minMembership, membership);
                }
            }
            sumCoverages += minMembership;
        }

        return (float) (sumCoverages / dataSize);
    }

    public float T4(List<List<Double>> data, List<LinguisticVariable> summarizers, float T3Value) {
        double productOfR = 1.0;
        int dataSize = data.get(0).size();

        // Calculate the product of values r for each summarizer
        for (int i = 0; i < summarizers.size(); i++) {
            LinguisticVariable summarizer = summarizers.get(i);
            double sumMembership = 0.0;

            // Calculate the sum of memberships for each summarizer
            for (int index = 0; index < dataSize; index++) {
                double value = data.get(i).get(index);
                List<FuzzySet> fuzzySets = summarizer.getFuzzySets();
                double maxMembership = Double.MIN_VALUE;

                // Find the maximum membership for each value
                for (FuzzySet fuzzySet : fuzzySets) {
                    double membership = fuzzySet.calculateMembership(value);
                    maxMembership = Math.max(maxMembership, membership);
                }
                // Add the maximum membership to the sum
                sumMembership += maxMembership;
            }

            // Calculate r for each summarizer and multiply it with the previous product
            double r = sumMembership / dataSize;
            productOfR *= r;
        }
        // Calculate T4 using the formula
        return Math.abs((float) (productOfR - T3Value));
    }

    private float T5(List<LinguisticVariable> summarizers){
        int p = 0;

        for(LinguisticVariable summarizer : summarizers){
            p += summarizer.getFuzzySets().size();
        }

        return (float) (2 * Math.pow(0.5, p));
    }

//    M = data.size() ?
    private float T6(Label quantifier, boolean isRelativeQuantifier, int M){
        float result = 0.0F;
        FuzzySet fuzzyQuantivier = quantifier.getLinguisticVariable().getMembershipFunction(quantifier.getSetName());

        if(isRelativeQuantifier){
            result = (float ) (1 - Math.abs(fuzzyQuantivier.getSupport().size()));
        }else {
            result = (float ) (1 - Math.abs(fuzzyQuantivier.getSupport().size()) / M);
        }

        return result;
    }

    private float T7(Label quantifier, boolean isRelativeQuantifier, int M){
        float result = 0.0F;
        FuzzySet fuzzyQuantivier = quantifier.getLinguisticVariable().getMembershipFunction(quantifier.getSetName());

        if(isRelativeQuantifier){
            result = (float ) (1 - Math.abs(fuzzyQuantivier.getCardinality()));
        }else {
            result = (float ) (1 - Math.abs(fuzzyQuantivier.getCardinality()) / M);
        }

        return result;
    }

    private float T8(List<LinguisticVariable> summarizers) {
        double result = 0.0;
        double totalResult = 0.0;
        double maxMembership = Double.MIN_VALUE;

        for (LinguisticVariable summarizer : summarizers) {

            for(FuzzySet fuzzySet : summarizer.getFuzzySets()) {
                for(Double position : fuzzySet.getSupport()){
                    double membership = fuzzySet.calculateMembership(position);
                    maxMembership = Math.max(maxMembership, membership);
                }
                result *= fuzzySet.getCardinality();
            }
            totalResult += Math.pow(result, 1.0/summarizer.getFuzzySets().size());
        }

        return 1 - (float) totalResult;
    }

    private float T9(Label qualifier){
        float result = 0.0F;

        for(FuzzySet fuzzySet : qualifier.getLinguisticVariable().getFuzzySets()){
            result *= (float) fuzzySet.getImprecision();
        }

        return 1 - (float) Math.pow(result, (double) 1 /qualifier.getLinguisticVariable().getFuzzySets().size());
    }

    private float T10(Label qualifier){
        float result = 0.0F;
        double maxMembership = Double.MIN_VALUE;

        for(FuzzySet fuzzySet : qualifier.getLinguisticVariable().getFuzzySets()) {
            for (Double position : fuzzySet.getSupport()) {
                double membership = fuzzySet.calculateMembership(position);
                maxMembership = Math.max(maxMembership, membership);
            }
            result *= (float) (fuzzySet.getCardinality() / maxMembership);
        }

        return 1 - (float) Math.pow(result, (double) 1 /qualifier.getLinguisticVariable().getFuzzySets().size());
    }


    private float T11(Label qualifiers) {
        return (float) (2 - (0.5 * qualifiers.getLinguisticVariable().getFuzzySets().size()));
    }







//----------------------------------------------------------------------------------------------------------------------
//    private float T1(List<Double> data, List<Label> summarizers, boolean isRelativeQuantifier) {
//        double sumMemberships = 0.0;
//        double sumQuantifierMemberships = 0.0;
//
//        for (Double value : data) {
//            double minMembership = Double.MAX_VALUE;
//            for (Label summarizer : summarizers) {
//                FuzzySet fuzzySet = summarizer.getLinguisticVariable().getMembershipFunction(summarizer.getSetName());
//                minMembership = Math.min(minMembership, fuzzySet.calculateMembership(value));
//            }
//            sumMemberships += minMembership;
//
//            if (isRelativeQuantifier) {
//                sumQuantifierMemberships += 1;
//            } else {
//                sumQuantifierMemberships += minMembership;
//            }
//        }
//
//        if (isRelativeQuantifier) {
//            return (float) (sumMemberships / data.size());
//        } else {
//            return (float) (sumMemberships / sumQuantifierMemberships);
//        }
//    }
}
