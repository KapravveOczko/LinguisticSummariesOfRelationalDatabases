package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.List;

public abstract class LinguisticSummary<T> {

    private Label qualifier;
    private List<T> subject;
    private List<Label> summarizer;
    private TruthChecker truthChecker;
    private Label quantifier;


    public String createLinguisticSummary(){
        return null;
    }


}
