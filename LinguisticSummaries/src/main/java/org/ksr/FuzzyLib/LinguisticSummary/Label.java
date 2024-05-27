package org.ksr.FuzzyLib.LinguisticSummary;

import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

public class Label {
    private String setName;
    private LinguisticVariable linguisticVariable;

    public Label(String setName, LinguisticVariable linguisticVariable) {
        this.setName = setName;
        this.linguisticVariable = linguisticVariable;
    }

    public Label(Label other) {
        this.setName = other.setName;
        this.linguisticVariable = other.linguisticVariable;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public LinguisticVariable getLinguisticVariable() {
        return linguisticVariable;
    }

    public void setLinguisticVariable(LinguisticVariable linguisticVariable) {
        this.linguisticVariable = linguisticVariable;
    }
}
