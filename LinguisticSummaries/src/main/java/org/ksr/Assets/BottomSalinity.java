package org.ksr.Assets;
import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;

import java.io.IOException;

public class BottomSalinity extends LinguisticVariable {

    public BottomSalinity() {
        super("sea_bottom_salinity");
//        appendMembershipFunctions();
        try {
            loadMembershipFunctions("BottomSalinity");
        } catch (IOException e) {
            System.out.println("BottomSalinity");
            throw new RuntimeException(e);
        }
    }


    public void appendMembershipFunctions(){
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "not salty",  20.0, 20.0, 22.0, 26.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "moderately salty", 24.0, 26.0, 30.5, 32.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRIANGULAR, "strongly salty", 30.5, 34.0, 36.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "really salty", 35.0, 36.0, 38.0,  38.0));
    }

    @Override
    public String toText(String fuzzySetName){
        return "that water is " + fuzzySetName + " on the bottom";
    }

}
