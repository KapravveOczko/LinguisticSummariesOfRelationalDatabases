package org.ksr.FuzzyLib.LinguisticVariable;

import org.ksr.FuzzyLib.FuzzySet.FuzzySet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class LinguisticVariable {
    private String name;
    private List<FuzzySet> fuzzySets;


    public LinguisticVariable(String name) {
        this.name = name;
        this.fuzzySets = new ArrayList<FuzzySet>();
    }


    @Override
    public String toString() {
        return this.name;
    }

    public List<FuzzySet> getMembershipFunctions() {
        return fuzzySets;
    }

    public void appendMembershipFunction(FuzzySet fuzzySet){
        Optional<FuzzySet> functionToRemove = this.fuzzySets.stream()
                .filter(function -> fuzzySet.getName().equals(function.getName()))
                .findFirst();
        if (functionToRemove.isPresent()) {
            throw new RuntimeException("There is a membership functions with the specified name");
        } else {
            this.fuzzySets.add(fuzzySet);
        }
    }

    public void deleteMembershipFunction(String functionName) {
        Optional<FuzzySet> functionToRemove = this.fuzzySets.stream()
                .filter(function -> functionName.equals(function.getName()))
                .findFirst();
        if (functionToRemove.isPresent()) {
            this.fuzzySets.remove(functionToRemove.get());
        } else {
            throw new RuntimeException("There are no membership functions with the specified name");
        }
    }


}
