package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRAPEZOIDAL;

public class Depth extends LinguisticVariable {

    public Depth() {
        super("mixed_layer_depth");
        appendMembershipFunctions();
    }

    public void appendMembershipFunctions(){
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "surface",0.0,0.0,4.0,10.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "shallow",6.0,10.0,14.0,16.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "moderately shallow",14.0,18.0,21.0,28.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "deep",21.0,29.0,32.0,34.0));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRAPEZOIDAL, "profoundly",32.0,34.0,40.0,40.0));
    }

    @Override
    public String toText(String fuzzySetName){
        if(fuzzySetName.equals("surface")){
            return "sea is almost on " + fuzzySetName + " level depth";
        }
        else {
            return "that sea is " + fuzzySetName;
        }
    }
}
