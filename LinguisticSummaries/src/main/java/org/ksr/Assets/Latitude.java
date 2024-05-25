package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;

public class Latitude extends LinguisticVariable {
    public Latitude() {
        super("latitude");
        appendMembershipFunctions();
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "north fo Ireland", 48.0, 48.0, 51.5, 52.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "latitude fo Ireland", 51.5, 52.0, 55.0, 56.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "south fo Ireland", 55.0, 56.0, 60.0, 60.0));
    }

    @Override
    public String toText(String fuzzySetName){
        if(fuzzySetName.equals("latitude fo Ireland")){
            return "that record was made on " + fuzzySetName;
        }
        else {
            return "that record was made " + fuzzySetName;
        }
    }
}
