package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.FuzzyLib.FuzzySet.FuzzySet;

import java.util.List;

public abstract class LinguisticSummary<T> {

    private FuzzySet qualifier;
    private T subject;
    private List<FuzzySet> summarizer;
    private TruthChecker truthChecker;
    private FuzzySet quantifier;


    public String createLinguisticSummary(){
        return null;
    }


}
