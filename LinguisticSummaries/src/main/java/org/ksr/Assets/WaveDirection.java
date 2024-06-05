package org.ksr.Assets;

import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;

import static org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants.TRIANGULAR;

public class WaveDirection extends LinguisticVariable {

    public WaveDirection() {
        super("mean_wave_direction");
//        appendMembershipFunctions();
        try {
            loadMembershipFunctions("WaveDirection");
        } catch (IOException e) {
            System.out.println("WaveDirection");
            throw new RuntimeException(e);
        }
    }

    public void appendMembershipFunctions() {
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "north", 0.0, 0.0, 32.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "north-east", 12.5, 45.0, 77.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "east", 57.5, 90.0, 122.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "south-east", 102.5, 135.0, 167.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "south", 147.5, 180.0, 212.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "south-west", 192.5, 225.0, 257.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "west", 237.5, 270.0, 302.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "north-west", 282.5, 315.0, 347.5));
        appendMembershipFunction(FuzzySetFactory.createMembershipFunction(TRIANGULAR, "north ", 327.5, 360.0, 360.0));
    }

    @Override
    public String toText(String fuzzySetName){
        return "that waves moves " + fuzzySetName;
    }
}
