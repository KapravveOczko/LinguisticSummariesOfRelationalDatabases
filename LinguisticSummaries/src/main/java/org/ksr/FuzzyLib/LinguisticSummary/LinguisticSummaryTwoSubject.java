package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.DataController.DatabaseConnector;
import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

public class LinguisticSummaryTwoSubject {

//    subject 1 and 2 would need to have the same name as the table in postage
    private String subjectOne;
    private String subjectTwo;
    private Label qualifier; //W
    private LinguisticVariable summarizer;
    private Label quantifier;
    private DatabaseConnector db;
    TruthCheckerTwoSubject truthCheckerTwoSubject;

    public LinguisticSummaryTwoSubject(DatabaseConnector db, String subjectOne, String subjectTwo, Label qualifier, LinguisticVariable summarizer, Label quantifier) {
        this.subjectOne = subjectOne;
        this.subjectTwo = subjectTwo;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.quantifier = quantifier;
        this.db = db;
        this.truthCheckerTwoSubject = TruthCheckerTwoSubject.getInstance();
    }

    public List<String> createLinguisticSummaryTwoSubject(){
        List<String> summaries = new ArrayList<>();

        summaries.addAll(createLinguisticSummaryTwoSubjectType1(this.subjectOne, this.subjectTwo));
        summaries.addAll(createLinguisticSummaryTwoSubjectType1(this.subjectTwo, this.subjectOne));

        summaries.addAll(createLinguisticSummaryTwoSubjectType2(this.subjectOne, this.subjectTwo));
        summaries.addAll(createLinguisticSummaryTwoSubjectType2(this.subjectTwo, this.subjectOne));

        summaries.addAll(createLinguisticSummaryTwoSubjectType3(this.subjectOne, this.subjectTwo));
        summaries.addAll(createLinguisticSummaryTwoSubjectType3(this.subjectTwo, this.subjectOne));

        summaries.addAll(createLinguisticSummaryTwoSubjectType4(this.subjectOne, this.subjectTwo));
        summaries.addAll(createLinguisticSummaryTwoSubjectType4(this.subjectTwo, this.subjectOne));

        return summaries;
    }

    private List<String> createLinguisticSummaryTwoSubjectType1(String firstSubject, String secondSubject){
        List<String> summaries = new ArrayList<>();
        String summary = "";

        for(FuzzySet fuzzySet : summarizer.getFuzzySets()){
            float degreeOfTruth =  this.truthCheckerTwoSubject.checkTruthType1();
            summary = this.quantifier.getSetName() + " " + firstSubject + " comparing to " + secondSubject + " show: "
                    + summarizer.toText(fuzzySet.getName()) + " [" + degreeOfTruth  + "]";
            summaries.add(summary);
        }

        return summaries;
    }

    private List<String> createLinguisticSummaryTwoSubjectType2(String firstSubject, String secondSubject){
        List<String> summaries = new ArrayList<>();
        String summary = "";

        for(FuzzySet fuzzySet : summarizer.getFuzzySets()){
            float degreeOfTruth =  this.truthCheckerTwoSubject.checkTruthType2();
            summary = this.quantifier.getSetName() + " " + firstSubject + " comparing to " + secondSubject + " having "
                    + this.qualifier.getLinguisticVariable().toText(qualifier.getSetName()) + " show: " + summarizer.toText(fuzzySet.getName()) + " [" + degreeOfTruth  + "]";

            summaries.add(summary);
        }

        return summaries;
    }

    private List<String> createLinguisticSummaryTwoSubjectType3(String firstSubject, String secondSubject){
        List<String> summaries = new ArrayList<>();
        String summary = "";

        for(FuzzySet fuzzySet : summarizer.getFuzzySets()){
            float degreeOfTruth =  this.truthCheckerTwoSubject.checkTruthType3();
            summary = this.quantifier.getSetName() + " " + firstSubject + " having "
                    + this.qualifier.getLinguisticVariable().toText(qualifier.getSetName()) + " comparing to " + secondSubject + " show: " + this.summarizer.toText(fuzzySet.getName()) + " [" + degreeOfTruth  + "]";

            summaries.add(summary);
        }

        return summaries;
    }

    private List<String> createLinguisticSummaryTwoSubjectType4(String firstSubject, String secondSubject){
        List<String> summaries = new ArrayList<>();
        String summary = "";

        for(FuzzySet fuzzySet : summarizer.getFuzzySets()){
            float degreeOfTruth =  this.truthCheckerTwoSubject.checkTruthType4();
            summary = "more " + firstSubject + " than " + secondSubject + " show: " + summarizer.toText(fuzzySet.getName()) + " [" + degreeOfTruth + "]";

            summaries.add(summary);
        }

        return summaries;
    }

}
