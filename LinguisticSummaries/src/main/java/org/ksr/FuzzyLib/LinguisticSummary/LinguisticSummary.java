package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.util.List;

public abstract class LinguisticSummary<T> {

    private LinguisticVariable qualifier;
    private List<T> subject;
    private List<LinguisticVariable> summarizer;
    private TruthChecker truthChecker;
    private LinguisticVariable quantifier;


    public String createLinguisticSummary(){
        return null;
    }


}
