package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;

public class Longitude extends LinguisticVariable {
    public Longitude() {
        super("longitude");
//        appendMembershipFunctions();
        try {
            loadMembershipFunctions("Longitude");
        } catch (IOException e) {
            System.out.println("Longitude");
            throw new RuntimeException(e);
        }
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "west of Ireland", -18.0, -18.0, -10.5, -9.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "longitude of Ireland", -10.5, -9.0, -6.3, -5.4));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "east of Ireland", -6.3, -5.4, -2.0, -2.0));
    }

    @Override
    public String toText(String fuzzySetName){
        if(fuzzySetName.equals("longitude fo Ireland")){
            return "that record was made on " + fuzzySetName;
        }
        else {
            return "that record was made " + fuzzySetName;
        }
    }
}
