package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;
import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRIANGULAR;

public class SurfaceTemperature extends LinguisticVariable {
    public SurfaceTemperature() {
        super("sea_surface_temperature");
        appendMembershipFunctions();
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "cold", 10.0, 10.0, 12.5, 14.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "cool", 13.0, 14.5, 16.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "moderate", 15.5, 17.0, 18.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "warm", 18.0, 19.5, 22.0, 22.0));
    }
}
