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
    public Float checkTruth(List<List<Double>> data,
                            List<List<Double>> rawData,
                            List<Label> summarizers,
                            List<Double>  qualifierData,
                            Label qualifier,
                            Label quantifier ) {

//        rawData = data not filterd by qualifier

        float result = 0.0F;
        boolean isRelativeQuantifier = false;

        if(quantifier.getLinguisticVariable().getName().equals("relative_quantifiers")){
            isRelativeQuantifier = true;
        }

//        result += T1(data, summarizers, isRelativeQuantifier);
//        result += T2(summarizers);
//        float T3Value = T3(data, qualifierData ,summarizers, qualifier);
//        result += T4(data, summarizers, T3Value);
//        result += T3Value;
//        result += T5(summarizers);
//        result += T6(quantifier, data.size());
//        result += T7(quantifier, data.size());
//        result += T8(data, summarizers);
//
//        if(qualifier!=null) {
//            result += T9(qualifier);
//            result += T10(qualifier);
//            result += T11(qualifier);
//            result += T4(data, summarizers, T3Value);
//            return result / 5;
//        }
//        else{
//            return result / 7;
//        }
        return result;
    }

    private float T1(List<List<Double>> data, List<Label> summarizers, boolean isRelativeQuantifier) {
        float result = 0.0F;

        for(int i=0; i!=summarizers.size(); i++)
        {
            for (int j=0; j!= data.size(); j++) {
                result += summarizers.get(i).getLinguisticVariable().getMembershipFunction(summarizers.get(i).getSetName()).calculateMembership(data.get(j).get(i));
            }

            if (isRelativeQuantifier) {
                result += result / data.size();
            } else {
                result += result;
            }
        }
        return result / summarizers.size();
    }

    //    5.24
    public float T2(List<Label> summarizers) {
        double productImprecision = 1.0;
        int p = summarizers.size();

        for (Label summarizer : summarizers) {
            FuzzySet fuzzySet = summarizer.getLinguisticVariable().getMembershipFunction(summarizer.getSetName());
            productImprecision *= fuzzySet.getImprecision();
        }

        return 1 - (float) Math.pow(productImprecision, p);
    }

//    public static float T3(List<List<Double>> data, List<Double> qualifierData, List<Label> summarizers, Label qualifier) {
//        double sumTi = 0.0;
//        double sumHi = 0.0;
//        int M = data.size();
//
//        FuzzySet qualifierSet = null;
//        if (qualifier != null) {
//            qualifierSet = qualifier.getLinguisticVariable().getMembershipFunction(qualifier.getSetName());
//        }
//
//        for (int i = 0; i < data.size(); i++) {
//            double ti = 1.0;
//            double hi = 1.0;
//            if (qualifierSet != null) {
//                hi = qualifierSet.calculateMembership(qualifierData.get(i));
//                if (!Double.isNaN(hi)) {
//                    ti *= hi;
//                }
//            }
//
//            List<Double> record = data.get(i);
//
//            for (int j = 0; j < summarizers.size(); j++) {
//                FuzzySet summarizerSet = summarizers.get(j).getLinguisticVariable().getMembershipFunction(summarizers.get(j).getSetName());
//                double membership = summarizerSet.calculateMembership(record.get(j));
//                if (!Double.isNaN(membership)) {
//                    ti *= membership;
//                }
//            }
//
//            if (!Double.isNaN(hi)) {
//                sumTi += ti;
//                sumHi += hi;
//            }
//        }
//
//        if (sumHi != 0) {
//            return (float) (sumTi / sumHi);
//        } else {
//            return (float) (sumTi / M);
//        }
//    }




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

    private float T5(List<Label> summarizers){
        return (float) (2 * Math.pow(0.5, summarizers.size()));
    }

    //    M = data.size() ?
    private float T6(Label quantifier, int M) {
        FuzzySet fuzzyQuantifier = quantifier.getLinguisticVariable().getMembershipFunction(quantifier.getSetName());
        int supportSize = fuzzyQuantifier.getSupport().size();
        return 1.0F - (float) supportSize / M;
    }


    private float T7(Label quantifier, int M) {
        FuzzySet fuzzyQuantifier = quantifier.getLinguisticVariable().getMembershipFunction(quantifier.getSetName());
        double cardinality = fuzzyQuantifier.getCardinality();
        return 1.0F - (float) (cardinality / M);
    }


    private float T8(List<List<Double>> data, List<Label> summarizers) {
        double productOfRelativeCardinalities = 1.0;
        int M = data.size();

        for (Label summarizer : summarizers) {
            FuzzySet fuzzySet = summarizer.getLinguisticVariable().getMembershipFunction(summarizer.getSetName());
            fuzzySet.calculateCardinality();  // Ensure cardinality is calculated

            double relativeCardinality = fuzzySet.getCardinality() / M;
            productOfRelativeCardinalities *= relativeCardinality;
        }

        double p = summarizers.size();
        double geometricMeanOfCardinalities = Math.pow(productOfRelativeCardinalities, 1.0 / p);

        return 1.0F - (float) geometricMeanOfCardinalities;
    }

//    private float T8(List<List<Double>> data, List<Label> summarizers) {
//        double result = 0.0;
//        double maxMembership = Double.MIN_VALUE;
//
//        for (int i=0; i!=summarizers.size(); i++) {
//            FuzzySet fuzzySet = summarizers.get(i).getLinguisticVariable().getMembershipFunction(summarizers.get(i).getSetName());
//            for(List<Double> value : data){
//                double membership = fuzzySet.calculateMembership(value.get(i));
//                maxMembership = Math.max(maxMembership, membership);
//            }
//            result *= fuzzySet.getCardinality()/maxMembership;
//        }
//
//        return 1 - (float) Math.pow(result, 1.0/summarizers.size());
//    }


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
