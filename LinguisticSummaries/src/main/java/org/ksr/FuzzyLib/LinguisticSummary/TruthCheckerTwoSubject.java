package org.ksr.FuzzyLib.LinguisticSummary;

import java.util.List;

public class TruthCheckerTwoSubject {

    private static TruthCheckerTwoSubject instance;

    private TruthCheckerTwoSubject() {
    }

    public static TruthCheckerTwoSubject getInstance() {
        if (instance == null) {
            instance = new TruthCheckerTwoSubject();
        }
        return instance;
    }

    public Float checkTruthType1() {
        return 0.0F;
    }

    public Float checkTruthType2() {
        return 0.0F;
    }

    public Float checkTruthType3() {
        return 0.0F;
    }

    public Float checkTruthType4() {
        return 0.0F;
    }
}
