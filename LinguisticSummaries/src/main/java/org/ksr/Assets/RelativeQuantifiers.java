package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;

public class RelativeQuantifiers extends LinguisticVariable {

    public RelativeQuantifiers() {
        super("relative_quantifiers");
        appendMembershipFunctions();
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "a few", 0.0, 0.0, 0.1, 0.3));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "about one third", 0.2, 0.3, 0.36, 0.46));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "almost_half", 0.4, 0.47, 0.53, 0.6));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "about two thirds", 0.53, 0.63, 0.7, 0.8));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "almost all", 0.7, 0.9, 1.0, 1.0));
    }


}
