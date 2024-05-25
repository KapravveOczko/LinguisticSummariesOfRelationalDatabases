package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;
import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRIANGULAR;

public class BottomTemperature extends LinguisticVariable {
    public BottomTemperature() {
        super("sea_bottom_temperature");
        appendMembershipFunctions();
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "cold", 0.0, 0.0, 3.0, 5.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "cool", 3.0, 6.0, 8.0, 9.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "moderate", 8.0, 10.0, 11.0, 13.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "warm", 12.0, 13.0, 14.0, 17.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "very warm", 15.5, 17.0, 18.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, " extremely warm", 17.5, 18.5, 24.0, 24.0));

    }

    @Override
    public String toText(String fuzzySetName){
        return fuzzySetName + " water temperature on sea bottom";
    }
}
