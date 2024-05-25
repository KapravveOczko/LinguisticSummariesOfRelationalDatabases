package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.GAUSSIAN;

public class WaveFrequency extends LinguisticVariable {

    public WaveFrequency() {
        super("mean_wave_period");
        appendMembershipFunctions();
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(GAUSSIAN, "wrinkles", 0.5, 0.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(GAUSSIAN, "rare wind waves", 2.6, 0.6));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(GAUSSIAN, "wind waves", 5.0, 0.75));
    }

    @Override
    public String toText(String fuzzySetName){
        return "that waves frequency was on a level of: " + fuzzySetName;
    }
}
