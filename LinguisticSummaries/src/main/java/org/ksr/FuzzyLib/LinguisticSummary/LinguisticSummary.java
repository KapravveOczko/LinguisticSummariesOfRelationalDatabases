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
    private TruthChecker truthChecker;
    private Label quantifier;
    private DatabaseConnector db;

    public LinguisticSummary(Label qualifier, List<LinguisticVariable> summarizers, Label quantifier) {
        this.qualifier = qualifier; //W
        this.summarizers = summarizers;
        this.truthChecker = TruthChecker.getInstance();
        this.quantifier = quantifier;
        connectToDb();
    }

    public List<String> createLinguisticSummary() {
        Set<String> summaries = new HashSet<>();

        // Get data for subjects based on the summarizer's linguistic variable
        List<ArrayList<Double>> data = new ArrayList<>();
        for( LinguisticVariable summarizer : this.summarizers){
        data.add(db.getDataFromColumn("test_small_data", summarizer.getName()));
        }

        // W implementation


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

                                // Store the summary (degree of truth calculation is skipped)
                                summaries.add(summary);
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

    public void connectToDb() {
        String url = "jdbc:postgresql://localhost:5432/ksr";
        String user = "postgres";
        String password = "pass";

        this.db = new DatabaseConnector(url, user, password);
    }
}


