package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;

public class WaveSpeed extends LinguisticVariable {
    public WaveSpeed() {
        super("sea_surface_velocity");
        appendMembershipFunctions();
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "practically no velocity", 0.0, 0.0, 0.05, 0.1));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "very low velocity", 0.05, 0.1, 0.2, 0.25));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "low velocity", 0.2, 0.25, 0.3, 0.4));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "high velocity", 0.3, 0.4, 0.475, 0.55));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "very high velocity", 0.475, 0.55, 0.7, 0.7));
    }

    @Override
    public String toText(String fuzzySetName){
        return "that waves were moving with " + fuzzySetName;
    }

}