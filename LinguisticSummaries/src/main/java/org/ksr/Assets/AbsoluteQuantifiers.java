package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;
import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRIANGULAR;

public class AbsoluteQuantifiers extends LinguisticVariable {

    public AbsoluteQuantifiers() {
        super("absolute_quantifiers");
//        appendMembershipFunctions();
        try {
            loadMembershipFunctions("AbsoluteQuantifiers");
        } catch (IOException e) {
            System.out.println("AbsoluteQuantifiers not found");
            throw new RuntimeException(e);
        }
    }

    private void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "lest than 10%", 0.0, 0.0, 0.1, 0.1));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "around 20%", 0.1, 0.2, 0.3));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "around 30%", 0.2, 0.3, 0.4));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "around 40%", 0.3, 0.4, 0.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "around 50%", 0.4, 0.5, 0.6));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "around 60%", 0.5, 0.6, 0.7));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "around 70%", 0.6, 0.7, 0.8));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "around 80%", 0.7, 0.8, 0.9));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "more than 90%", 0.9, 0.9, 1.0, 1.0));

    }

}
