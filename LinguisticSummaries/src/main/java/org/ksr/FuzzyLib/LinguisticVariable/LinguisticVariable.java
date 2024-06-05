package org.ksr.FuzzyLib.LinguisticVariable;

import org.ksr.DataController.JsonConnector;
import org.ksr.FuzzyLib.FuzzySet.FuzzySet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class LinguisticVariable {
    private String name;
    private List<FuzzySet> fuzzySets;
    private static final JsonConnector jsonConnector = new JsonConnector();


    public LinguisticVariable(String name) {
        this.name = name;
        this.fuzzySets = new ArrayList<>();
    }


    @Override
    public String toString() {
        return this.name;
    }

    public List<FuzzySet> getFuzzySets() {
        return fuzzySets;
    }

    public FuzzySet getMembershipFunction(String name){
        return getFuzzySets().stream()
                .filter(fuzzySet -> fuzzySet.getName().equals(name))
                .findFirst()
                .orElse(null);
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

    public void setFuzzySets(List<FuzzySet> fuzzySets) {
        this.fuzzySets = fuzzySets;
    }

    public String toText(String fuzzySetName){
        return fuzzySetName;
    }

    public String getName() {
        return name;
    }

    public JsonConnector getJsonConnector() {
        return jsonConnector;
    }

    protected void loadMembershipFunctions(String folderName) throws IOException {
        for(FuzzySet membershipFunction : this.jsonConnector.loadAllFuzzySetsFromFolder(folderName)){
            appendMembershipFunction(membershipFunction);
        }
    }
}
