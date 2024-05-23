package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.FuzzyLib.FuzzySet.FuzzySet;

import java.util.ArrayList;
import java.util.List;

public class TruthChecker {

    private List<List<Double>> data;
    private List<Label> summarizers;
    private Label qualifier;
    private Label quantifier;
    private int M; // Number of records in the database
    private boolean isRelativeQuantifier;

    public TruthChecker(List<List<Double>> data, List<Label> summarizers, Label qualifier, Label quantifier) {
        this.data = data;
        this.summarizers = summarizers;
        this.qualifier = qualifier;
        this.quantifier = quantifier;
        this.M = data.size();
        this.isRelativeQuantifier = quantifier.getLinguisticVariable().equals("relative_quantifiers");
    }

    // Will check degreeOfTruth using average from all truth checking methods
    public Float checkTruth() {
        List<Float> truthValues = new ArrayList<>();
        truthValues.add(degreeOfTruth());
        truthValues.add(DegreeOfFalsity());
        truthValues.add(degreeOfCovering());
        truthValues.add(degreeOfAppropriateness(truthValues.get(2)));
        truthValues.add(lengthOfSummary());
        truthValues.add(degreeOfQuantifierImprecision());
        truthValues.add(degreeOfQuantifierCardinality());
        truthValues.add(degreeOfSummarizerCardinality());
        truthValues.add(degreeOfQualifierImprecision());
        truthValues.add(degreeOfQualifierCardinality());
        truthValues.add(lengthOfQualifier());

        float sum = 0.0F;
        for (Float value : truthValues) {
            sum += value;
        }

        return sum / truthValues.size();
    }

    // T1 - Degree of truth
    private float degreeOfTruth() {
        return 0.0F;
    }

    // T2 - Degree of falsity
    public float DegreeOfFalsity() {
        double result = 0.0;
        double totalResult = 0.0;
        double maxMembership = Double.MIN_VALUE;

        for (Label summarizer : summarizers) {
            FuzzySet fuzzySet = summarizer.getLinguisticVariable().getMembershipFunction(summarizer.getSetName());
            for (Double position : fuzzySet.getSupport()) {
                double membership = fuzzySet.calculateMembership(position);
                maxMembership = Math.max(maxMembership, membership);
            }
            result *= fuzzySet.getCardinality();
        }
        totalResult += Math.pow(result, 1.0 / summarizers.size());

        return 1 - (float) totalResult;
    }

    // T3 - Degree of covering
    public float degreeOfCovering() {
        return 0.0F;
    }


    // T4 - Degree of appropriateness
    public float degreeOfAppropriateness(Float T3Value) {
        return 0.0F;
    }

    // T5 - Length of Summary
    private float lengthOfSummary() {
        return 0.0F;
    }

    // T6 - Degree of Quantifier Imprecision
    private float degreeOfQuantifierImprecision(){
        return 0.0F;
    }

    // T7 - Degree of Quantifier Cardinality
    private float degreeOfQuantifierCardinality(){
        return 0.0F;
    }

    // T8
    private float degreeOfSummarizerCardinality() {
        return 0.0F;
    }

    // T9
    private float degreeOfQualifierImprecision(){
        return 0.0F;
    }

    // T10
    private float degreeOfQualifierCardinality(){
        return 0.0F;
    }

    // T11
    private float lengthOfQualifier() {
        int q = qualifier.getLinguisticVariable().getFuzzySets().size();
        return (float) (2.0 * Math.pow(0.5, q));
    }


}
