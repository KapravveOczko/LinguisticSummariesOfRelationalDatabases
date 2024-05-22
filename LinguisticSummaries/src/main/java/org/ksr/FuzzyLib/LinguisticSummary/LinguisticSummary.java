package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.DataController.DatabaseConnector;
import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

//public class LinguisticSummary<T> {
public class LinguisticSummary {

    private Label qualifier;
//    private List<T> subject;
    private List<Label> summarizer;
    private TruthChecker truthChecker;
    private Label quantifier;
    private DatabaseConnector db;

    public LinguisticSummary(Label qualifier, List<Label> summarizer, Label quantifier) {
        this.qualifier = qualifier;
//        this.subject = subject;
        this.summarizer = summarizer;
        this.truthChecker = new TruthChecker();
        this.quantifier = quantifier;
        connectToDb();
    }

    public List<String> createLinguisticSummary() {
        List<String> summaries = new ArrayList<>();

        // Generate all possible combinations of summarizers (max 3)
        for (int i = 0; i < summarizer.size(); i++) {
            for (int j = i; j < summarizer.size(); j++) {
                for (int k = j; k < summarizer.size(); k++) {
                    List<Label> currentSummarizers = new ArrayList<>();
                    currentSummarizers.add(summarizer.get(i));
                    if (j != i) currentSummarizers.add(summarizer.get(j));
                    if (k != j && k != i) currentSummarizers.add(summarizer.get(k));

                    // Get data for subjects based on the first summarizer's linguistic variable
                    List<Double> data = db.getDataFromColumn("test_small_data", currentSummarizers
                            .get(0).getLinguisticVariable().getName());

                    // Generate summary text
                    String summary = generateSummaryText(currentSummarizers);

                    // Calculate truth degree for the summary
//                    double truthDegree = calculateTruthDegree(data, currentSummarizers);

                    //tmp:
                    double truthDegree = 0.0;

                    // Store the summary and its truth degree
                    summaries.add(summary + " - [ " + truthDegree + " ]") ;
                }
            }
        }

        return summaries;
    }

    private String generateSummaryText(List<Label> summarizers) {
        StringBuilder summary = new StringBuilder();

        // Add quantifier
        summary.append(quantifier.getSetName()).append(" ");

        // Add subject
        summary.append("records are ");

        // Add summarizers
        for (int i = 0; i < summarizers.size(); i++) {
            if (i > 0) {
                summary.append(" and ");
            }
            summary.append(summarizers.get(i).getSetName());
        }

        return summary.toString();
    }

//    private double calculateTruthDegree(List<Double> data, List<Label> summarizers) {
//        double truthDegree = 0.0;
//        int count = 0;
//
//        for (Double value : data) {
//            double membership = calculateMembership(value, summarizers);
//            truthDegree += membership;
//            count++;
//        }
//
//        return truthDegree / count;
//    }
//
//    private double calculateMembership(Double value, List<Label> summarizers) {
//        double membership = 1.0;
//
//        for (Label summarizer : summarizers) {
//            FuzzySet fuzzySet = summarizer.getLinguisticVariable().getMembershipFunction(summarizer.getSetName());
//            membership = Math.min(membership, fuzzySet.calculateMembership(value));
//        }
//
//        return membership;
//    }

    public void connectToDb() {
        String url = "jdbc:postgresql://localhost:5432/ksr";
        String user = "postgres";
        String password = "pass";

        this.db = new DatabaseConnector(url, user, password);
    }
}
