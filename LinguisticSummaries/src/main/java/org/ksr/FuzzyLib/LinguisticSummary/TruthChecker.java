package org.ksr.FuzzyLib.LinguisticSummary;

import java.util.List;

public class TruthChecker {
    private static TruthChecker instance;

    public TruthChecker() {
    }

    public static TruthChecker getInstance() {
        if (instance == null) {
            instance = new TruthChecker();
        }
        return instance;
    }

    // will check degreeOfTruth using average from all truth checking methods
    public Float checkTruth(){
        return null;
    }

    // will check degreeOfTruth using average from all truth using selected methods
    public Float checkCertainTruth(List<Integer> selectedMethods ){
        Float truth = 0.0F;

        for(Integer method: selectedMethods){
            switch (method){
//                case TruthCheckerConsts.T1 -> truth += T1();
//                case TruthCheckerConsts.T2 -> truth += T2();
//                case TruthCheckerConsts.T3 -> truth += T3();
                // [...]
            }
        }

        return truth/selectedMethods.size();
    }

    private Float T4toT11(){return null;}
}
