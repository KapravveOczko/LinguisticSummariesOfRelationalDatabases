package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;

public class Latitude extends LinguisticVariable {
    public Latitude() {
        super("latitude");
//        appendMembershipFunctions();
        try {
            loadMembershipFunctions("Latitude");
        } catch (IOException e) {
            System.out.println("Latitude");
            throw new RuntimeException(e);
        }
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "north of Ireland", 48.0, 48.0, 51.5, 52.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "latitude of Ireland", 51.5, 52.0, 55.0, 56.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "south of Ireland", 55.0, 56.0, 60.0, 60.0));
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
