package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.FuzzyLib.FuzzySet.FuzzySet;

import java.util.List;

public abstract class LinguisticSummary<T> {

    private FuzzySet qualifier;
    private T object;
    private List<FuzzySet> summarizer;
    private Float degreeOfTruth;
    private FuzzySet quantifier;


    public StringBuilder createLinguisticSummary(FuzzySet qualifier, T object, FuzzySet totalizator){
        return null;
    }

    public StringBuilder createLinguisticSummary(FuzzySet qualifier, T object, FuzzySet quantifier, FuzzySet totalizator){
        return null;
    }

}
