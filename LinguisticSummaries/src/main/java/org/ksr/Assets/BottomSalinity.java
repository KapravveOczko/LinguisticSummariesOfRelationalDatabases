package org.ksr.Assets;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;

public class BottomSalinity extends LinguisticVariable {

    public BottomSalinity() {
        super("sea_bottom_salinity");
        appendMembershipFunctions();
    }


    public void appendMembershipFunctions(){
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "not_salty",  20.0, 20.0, 22.0, 26.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "moderately_salty", 24.0, 26.0, 30.5, 32.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRIANGULAR, "strongly_salty", 30.5, 34.0, 36.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "really_salty", 35.0, 36.0, 38.0,  38.0));
    }


}