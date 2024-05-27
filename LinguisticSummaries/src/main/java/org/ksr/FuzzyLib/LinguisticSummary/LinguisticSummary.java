package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.DataController.DatabaseConnector;
import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinguisticSummary {

    private Label qualifier; //W
    private List<LinguisticVariable> summarizers;
    private Label quantifier;
    private DatabaseConnector db;
    TruthChecker truthChecker;

    public LinguisticSummary(DatabaseConnector db, Label qualifier, List<LinguisticVariable> summarizers, Label quantifier) {
        this.qualifier = qualifier; //W
        this.summarizers = summarizers;
        this.quantifier = quantifier;
        this.truthChecker = TruthChecker.getInstance();
        this.db = db;
    }

    public List<String> createLinguisticSummary() {
        Set<String> summaries = new HashSet<>();

        // Get data for subjects based on the summarizer's linguistic variable
        List<List<Double>> data = new ArrayList<>();

        for (LinguisticVariable summarizer : this.summarizers) {
            data.add(db.getDataFromColumn("test_full_data", summarizer.getName()));
        }

        List<List<Double>> filteredData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            filteredData.add(new ArrayList<>());
        }

        List<Double> qualifierColumn = new ArrayList<>();
        // Filter data based on the qualifier (W)
        if (this.qualifier != null) {
            qualifierColumn = db.getDataFromColumn("test_full_data", this.qualifier.getLinguisticVariable().getName());
            FuzzySet fuzzySetQualifier = qualifier.getLinguisticVariable().getMembershipFunction(qualifier.getSetName());

            for (int i = 0; i < qualifierColumn.size(); i++) {
                if (fuzzySetQualifier.calculateMembership(qualifierColumn.get(i)) > 0) {
                    for(int j=0; j != this.summarizers.size(); j++) {
                        filteredData.get(j).add(data.get(j).get(i));
                    }
                }
            }
        } else {
            for (int i = 0; i < data.size(); i++) {
                filteredData.get(i).addAll(data.get(i));
            }
        }

        ///////////////////////////
//        System.out.println("------------------------");
//        for (int i = 0; i != data.size(); i++) {
//            System.out.println(data.get(i));
//        }
//        System.out.println("------------------------");
//        System.out.println("------------------------");
//        for (int i = 0; i != filteredData.size(); i++) {
//            System.out.println(filteredData.get(i));
//        }
//        System.out.println("------------------------");
        //////////////////////////

        // Generate all possible combinations of summarizers (max 3)
        for (int i = 0; i < summarizers.size(); i++) {
            LinguisticVariable var1 = summarizers.get(i);
            for (int j = i; j < summarizers.size(); j++) {
                LinguisticVariable var2 = summarizers.get(j);
                for (int k = j; k < summarizers.size(); k++) {
                    LinguisticVariable var3 = summarizers.get(k);

                    // Generate combinations of fuzzy sets from the three variables
                    for (FuzzySet set1 : var1.getFuzzySets()) {
                        for (FuzzySet set2 : var2.getFuzzySets()) {
                            for (FuzzySet set3 : var3.getFuzzySets()) {
                                List<Label> currentSummarizers = new ArrayList<>();
                                currentSummarizers.add(new Label(set1.getName(), var1));
                                if (j != i) currentSummarizers.add(new Label(set2.getName(), var2));
                                if (k != j && k != i) currentSummarizers.add(new Label(set3.getName(), var3));

                                // Generate summary text
                                String summary = generateSummaryText(currentSummarizers);
                                 float degreeOfTruth =  truthChecker.checkTruth(filteredData, data, currentSummarizers, qualifierColumn, qualifier, quantifier);

                                // Store the summary (degree of truth calculation is skipped)
                                 summaries.add(summary + " [" + degreeOfTruth + "]");
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(summaries);
    }


    private String generateSummaryText(List<Label> summarizers) {
        StringBuilder summary = new StringBuilder();

        // Add quantifier
        summary.append(quantifier.getSetName()).append(" ");

        // Add subject
        summary.append("records ");

        // Add qualifier
        if(this.qualifier != null) {
            String qualifierName = this.qualifier.getSetName();
            summary.append("having ").append(this.qualifier.getLinguisticVariable().toText(qualifierName)).append(" show: ");
        }
        else {
            summary.append("show: ");
        }

        // Add summarizers
        for (int i = 0; i < summarizers.size(); i++) {
            if (i > 0) {
                summary.append(", ");
            }
            String summaryName = summarizers.get(i).getSetName();
            summary.append(summarizers.get(i).getLinguisticVariable().toText(summaryName));
        }

        return summary.toString();
    }
}


