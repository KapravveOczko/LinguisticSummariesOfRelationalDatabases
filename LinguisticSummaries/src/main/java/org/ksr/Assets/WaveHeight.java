package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;
import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRIANGULAR;

public class WaveHeight extends LinguisticVariable {
    public WaveHeight() {
        super("significant_wave_height");
//        appendMembershipFunctions();
        try {
            loadMembershipFunctions("WaveHeight");
        } catch (IOException e) {
            System.out.println("WaveHeight");
            throw new RuntimeException(e);
        }
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "calm seas", 0.0, 0.0, 0.5, 0.7));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "moderately height", 0.5, 1.2, 1.8, 2.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "medium height", 1.8, 2.5, 2.7));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "height", 2.6, 2.7, 3.0, 3.0));
    }

    @Override
    public String toText(String fuzzySetName){
        if(fuzzySetName.equals("calm seas")){
            return fuzzySetName;
        }
        else {
            return fuzzySetName + " waves";
        }
    }
}
