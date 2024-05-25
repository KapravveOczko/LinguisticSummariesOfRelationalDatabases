package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

public class SurfaceSalinity extends LinguisticVariable {

    public SurfaceSalinity() {
        super("sea_surface_salinity");
        appendMembershipFunctions();

    }

    public void appendMembershipFunctions(){
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "moderately_salty",  20.0, 20.0, 26.0, 30.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "salty", 26.0, 30.0, 33.0, 35.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, "strongly_salty", 34.0, 35.0, 40.0, 40.0));
    }

    @Override
    public String toText(String fuzzySetName){
        return "that water is " + fuzzySetName + " on the surface";
    }

}
