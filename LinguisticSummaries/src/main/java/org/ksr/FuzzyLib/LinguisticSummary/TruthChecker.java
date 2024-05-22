package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
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
    public Float checkTruth(List<Double> data, List<Label> summarizers) {
        float T1 = T1(data, summarizers);
        float T2 = T2(data, summarizers);
        float T3 = T3(data, summarizers);
        float T4 = T4(data, summarizers);
        float T5 = T5(data, summarizers);
        float T6 = T6(data, summarizers);
        float T7 = T7(data, summarizers);
        float T8 = T8(data, summarizers);
        float T9 = T9(data, summarizers);
        float T10 = T10(data, summarizers);
        float T11 = T11(data, summarizers);

        // Compute the average of all truth values
        return (T1 + T2 + T3 + T4 + T5 + T6 + T7 + T8 + T9 + T10 + T11) / 11;
    }

    private float T1(List<Double> data, List<Label> summarizers) {
        // Degree of truth for the summarizer
        return calculateMembership(data, summarizers);
    }

    private float T2(List<Double> data, List<Label> summarizers) {
        // Degree of Imprecision
        return 1 - (1.0f / summarizers.size());
    }

    private float T3(List<Double> data, List<Label> summarizers) {
        // Degree of Covering
        return calculateMembership(data, summarizers) / data.size();
    }

    private float T4(List<Double> data, List<Label> summarizers) {
        // Degree of Appropriateness
        return calculateMembership(data, summarizers) / summarizers.size();
    }

    private float T5(List<Double> data, List<Label> summarizers) {
        // Length of the summarizer
        return (float) (2.0 / (1 + Math.exp(-summarizers.size())));
    }

    private float T6(List<Double> data, List<Label> summarizers) {
        // Degree of Quantifier Imprecision
        return 1 - (1.0f / data.size());
    }

    private float T7(List<Double> data, List<Label> summarizers) {
        // Degree of Quantifier Cardinality
        return (float) summarizers.size() / data.size();
    }

    private float T8(List<Double> data, List<Label> summarizers) {
        // Degree of Summarizer Cardinality
        return calculateMembership(data, summarizers);
    }

    private float T9(List<Double> data, List<Label> summarizers) {
        // Degree of Qualifier Cardinality
        return 1 - calculateMembership(data, summarizers);
    }

    private float T10(List<Double> data, List<Label> summarizers) {
        // Degree of Length of qualifier
        return (float) (2.0 / (1 + Math.exp(-summarizers.size())));
    }

    private float T11(List<Double> data, List<Label> summarizers) {
        // Degree of Qualifier Imprecision
        return 1 - (1.0f / summarizers.size());
    }

    private float calculateMembership(List<Double> data, List<Label> summarizers) {
        float membership = 0.0f;

        for (Double value : data) {
            float minMembership = Float.MAX_VALUE;

            for (Label summarizer : summarizers) {
                FuzzySet fuzzySet = summarizer.getLinguisticVariable().getMembershipFunction(summarizer.getSetName());
                minMembership = Math.min(minMembership, fuzzySet.calculateMembership(value).floatValue());
            }

            membership += minMembership;
        }

        return membership / data.size();
    }
}
