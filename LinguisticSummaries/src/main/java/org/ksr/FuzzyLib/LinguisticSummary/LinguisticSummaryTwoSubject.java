package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.DataController.DatabaseConnector;
import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinguisticSummaryTwoSubject {

    //    subject 1 and 2 would need to have the same name as the table in postage
    private String subjectOne;
    private String subjectTwo;
    private Label qualifier; //W
    private List<LinguisticVariable> summarizers;
    private Label quantifier;
    private DatabaseConnector db;
    TruthCheckerTwoSubject truthCheckerTwoSubject;

    public LinguisticSummaryTwoSubject(DatabaseConnector db, String subjectOne, String subjectTwo, Label qualifier, List<LinguisticVariable> summarizers, Label quantifier) {
        this.subjectOne = subjectOne;
        this.subjectTwo = subjectTwo;
        this.qualifier = qualifier;
        this.summarizers = summarizers;
        this.quantifier = quantifier;
        this.db = db;
        this.truthCheckerTwoSubject = TruthCheckerTwoSubject.getInstance();
    }

    public List<String> createLinguisticSummaryTwoSubject() {
        Set<String> summaries = new HashSet<>();
        Set<String> summariesType1 = new HashSet<>();
        Set<String> summariesType2 = new HashSet<>();
        Set<String> summariesType3 = new HashSet<>();
        Set<String> summariesType4 = new HashSet<>();

        // Get data for subjects based on the summarizer's linguistic variable
        List<List<Double>> dataSubjectOne = new ArrayList<>();
        List<List<Double>> dataSubjectTwo = new ArrayList<>();

        for (LinguisticVariable summarizer : this.summarizers) {
//            dataSubjectOne.add(db.getDataFromColumn(subjectOne, summarizer.getName()));
//            dataSubjectTwo.add(db.getDataFromColumn(subjectTwo, summarizer.getName()));
            dataSubjectOne.add(db.getDataFromColumn("test_small_data", summarizer.getName()));
            dataSubjectTwo.add(db.getDataFromColumn("test_small_data", summarizer.getName()));
        }

        List<List<Double>> filteredDataOne = new ArrayList<>();
        List<List<Double>> filteredDataTwo = new ArrayList<>();

        for (int i = 0; i < dataSubjectOne.size(); i++) {
            filteredDataOne.add(new ArrayList<>());
        }
        for (int i = 0; i < dataSubjectTwo.size(); i++) {
            filteredDataTwo.add(new ArrayList<>());
        }

        List<Double> qualifierColumnOne = new ArrayList<>();
        List<Double> qualifierColumnTwo = new ArrayList<>();
        // Filter data based on the qualifier (W)
        if (this.qualifier != null) {
//            qualifierColumnOne = db.getDataFromColumn(this.subjectOne, this.qualifier.getLinguisticVariable().getName());
            qualifierColumnOne = db.getDataFromColumn("test_small_data", this.qualifier.getLinguisticVariable().getName());
            FuzzySet fuzzySetQualifierOne = qualifier.getLinguisticVariable().getMembershipFunction(qualifier.getSetName());

//            qualifierColumnTwo = db.getDataFromColumn(this.subjectTwo, this.qualifier.getLinguisticVariable().getName());
            qualifierColumnTwo = db.getDataFromColumn("test_small_data", this.qualifier.getLinguisticVariable().getName());
            FuzzySet fuzzySetQualifierTwo = qualifier.getLinguisticVariable().getMembershipFunction(qualifier.getSetName());

            for (int i = 0; i < qualifierColumnOne.size(); i++) {
                if (fuzzySetQualifierOne.calculateMembership(qualifierColumnOne.get(i)) > 0) {
                    for (int j = 0; j < this.summarizers.size(); j++) {
                        filteredDataOne.get(j).add(dataSubjectOne.get(j).get(i));
                    }
                }
            }

            for (int i = 0; i < qualifierColumnTwo.size(); i++) {
                if (fuzzySetQualifierTwo.calculateMembership(qualifierColumnTwo.get(i)) > 0) {
                    for (int j = 0; j < this.summarizers.size(); j++) {
                        filteredDataTwo.get(j).add(dataSubjectTwo.get(j).get(i));
                    }
                }
            }
        } else {
            for (int i = 0; i < dataSubjectOne.size(); i++) {
                filteredDataOne.get(i).addAll(dataSubjectOne.get(i));
                filteredDataTwo.get(i).addAll(dataSubjectTwo.get(i));
            }
        }


        ///////////////////////////
//        System.out.println("------------------------");
//        for (int i = 0; i != dataSubjectOne.size(); i++) {
//            System.out.println(dataSubjectOne.get(i));
//        }
//        System.out.println("------------------------------------------------------------------------------------------------");
//        System.out.println("------------------------------------------------------------------------------------------------");
//        for (int i = 0; i != filteredDataOne.size(); i++) {
//            System.out.println(filteredDataOne.get(i));
//        }
//        System.out.println("------------------------");
//        ////////////////////////

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
                                summariesType1.add(createLinguisticSummaryTwoSubjectType1(this.subjectOne, this.subjectTwo, currentSummarizers, filteredDataOne, filteredDataTwo));
                                summariesType1.add(createLinguisticSummaryTwoSubjectType1(this.subjectTwo, this.subjectOne, currentSummarizers, filteredDataOne, filteredDataTwo));
                                if (qualifier != null) {
                                    summariesType2.add(createLinguisticSummaryTwoSubjectType2(this.subjectOne, this.subjectTwo, currentSummarizers));
                                    summariesType2.add(createLinguisticSummaryTwoSubjectType2(this.subjectTwo, this.subjectOne, currentSummarizers));
                                    summariesType3.add(createLinguisticSummaryTwoSubjectType3(this.subjectOne, this.subjectTwo, currentSummarizers));
                                    summariesType3.add(createLinguisticSummaryTwoSubjectType3(this.subjectTwo, this.subjectOne, currentSummarizers));
                                }
                                summariesType4.add(createLinguisticSummaryTwoSubjectType4(this.subjectOne, this.subjectTwo, currentSummarizers));
                                summariesType4.add(createLinguisticSummaryTwoSubjectType4(this.subjectTwo, this.subjectOne, currentSummarizers));

                            }
                        }
                    }
                }
            }
        }

        summaries.addAll(summariesType1);
//        summaries.addAll(summariesType2);
//        summaries.addAll(summariesType3);
//        summaries.addAll(summariesType4);

        return new ArrayList<>(summaries);
    }

    private String createLinguisticSummaryTwoSubjectType1(String firstSubject, String secondSubject, List<Label> summarizers,List<List<Double>> filteredDataOne,List<List<Double>> filteredDataTwo) {
        StringBuilder summary = new StringBuilder();

        float degreeOfTruth = this.truthCheckerTwoSubject.checkTruthType1(filteredDataOne, filteredDataTwo, summarizers);
        summary.append(this.quantifier.getSetName()).append(" ").append(firstSubject).append(" comparing to ").append(secondSubject).append(" show: ").append(appendSummarizers(summarizers)).append(" [").append(degreeOfTruth).append("]");

        return summary.toString();
    }

    private String createLinguisticSummaryTwoSubjectType2(String firstSubject, String secondSubject, List<Label> summarizers) {
        StringBuilder summary = new StringBuilder();

        float degreeOfTruth = this.truthCheckerTwoSubject.checkTruthType2();

        summary.append(this.quantifier.getSetName()).append(" ").append(firstSubject).append(" comparing to ").append(secondSubject).append(" having ").append(this.qualifier.getLinguisticVariable().toText(qualifier.getSetName())).append(" show: ").append(appendSummarizers(summarizers)).append(" [").append(degreeOfTruth).append("]");

        return summary.toString();
    }

    private String createLinguisticSummaryTwoSubjectType3(String firstSubject, String secondSubject, List<Label> summarizers) {
        StringBuilder summary = new StringBuilder();

        float degreeOfTruth = this.truthCheckerTwoSubject.checkTruthType3();

        summary.append(this.quantifier.getSetName()).append(" ").append(firstSubject).append(" having ").append(this.qualifier.getLinguisticVariable().toText(qualifier.getSetName())).append(" comparing to ").append(secondSubject).append(" show: ").append(appendSummarizers(summarizers)).append(" [").append(degreeOfTruth).append("]");

        return summary.toString();
    }

    private String createLinguisticSummaryTwoSubjectType4(String firstSubject, String secondSubject, List<Label> summarizers) {
        StringBuilder summary = new StringBuilder();

        float degreeOfTruth = this.truthCheckerTwoSubject.checkTruthType4();
        summary.append("more ").append(firstSubject).append(" than ").append(secondSubject).append(" show: ").append(appendSummarizers(summarizers)).append(" [").append(degreeOfTruth).append("]");

        return summary.toString();
    }

    private String appendSummarizers(List<Label> summarizers) {
        StringBuilder summary = new StringBuilder();

        for (Label summarizer : summarizers) {
            summary.append(summarizer.getLinguisticVariable().toText(summarizer.getSetName()));
            if (!summarizer.equals(summarizers.getLast())) {
                summary.append(", ");
            }
        }

        return summary.toString();
    }

}
