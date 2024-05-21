package org.ksr.Assets;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;

public class BottomSalinity extends LinguisticVariable {

    public BottomSalinity(String name) {
        super(name);
        appendMembershipFunctions();
    }


    public void appendMembershipFunctions(){
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "not salty",  Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 22.0, 26.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "moderately salty", 24.0, 26.0, 30.5, 32.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRIANGULAR, "strongly salty", 30.5, 34.0, 36.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "really salty", 35.0, 36.0, Double.POSITIVE_INFINITY,  Double.POSITIVE_INFINITY));
    }


}
