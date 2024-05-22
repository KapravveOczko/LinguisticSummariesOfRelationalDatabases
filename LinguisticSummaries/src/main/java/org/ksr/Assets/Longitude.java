package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;

public class Longitude extends LinguisticVariable {
    public Longitude() {
        super("longitude");
        appendMembershipFunctions();
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "west fo Ireland", -18.0, -18.0, -10.5, -9.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "longitude fo Ireland", -10.5, -9.0, -6.3, -5.4));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "east fo Ireland", -6.3, -5.4, -2.0, -2.0));
    }
}
