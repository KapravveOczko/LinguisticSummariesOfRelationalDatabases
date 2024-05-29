package org.ksr.FuzzyLib.LinguisticSummary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TruthCheckerTwoSubject {

    private static TruthCheckerTwoSubject instance;

    private TruthCheckerTwoSubject() {
    }

    private int iter = 0;

    public int getIter() {
        return iter;
    }

    public void setIter(int iter) {
        this.iter = iter;
    }

    public static TruthCheckerTwoSubject getInstance() {
        if (instance == null) {
            instance = new TruthCheckerTwoSubject();
        }
        return instance;
    }

    public Float checkTruthType1to3(List<List<Double>> dataFirst, List<List<Double>> dataSecond, List<Label> summarizers) {

        float result = 0.0F;

        List<Label> summarizersFirst = new ArrayList<>();
        List<Label> summarizersSecond = new ArrayList<>();

        for (Label summarizer : summarizers) {
            summarizersFirst.add(new Label(summarizer));
            summarizersSecond.add(new Label(summarizer));
        }

        for(int i=0; i!= summarizers.size(); i++){
            summarizersFirst.get(i).getLinguisticVariable().getMembershipFunction(summarizersFirst.get(i).getSetName()).setValues(dataFirst.get(i));
            summarizersSecond.get(i).getLinguisticVariable().getMembershipFunction(summarizersSecond.get(i).getSetName()).setValues(dataSecond.get(i));
        }

        for(int i=0; i!=summarizers.size(); i++){

            if(summarizersFirst.get(i).getLinguisticVariable().getMembershipFunction(summarizersFirst.get(i).getSetName()).getCardinality().equals(0.0)){
                continue;
            }

            result += (float) ((1.0/dataFirst.getFirst().size() * summarizersFirst.get(i).getLinguisticVariable().getMembershipFunction(summarizersFirst.get(i).getSetName()).getCardinality())
                    /
                    (1.0/dataFirst.getFirst().size() * summarizersFirst.get(i).getLinguisticVariable().getMembershipFunction(summarizersFirst.get(i).getSetName()).getCardinality() +
                    1.0/dataSecond.getFirst().size() * summarizersSecond.get(i).getLinguisticVariable().getMembershipFunction(summarizersSecond.get(i).getSetName()).getCardinality()));
        }


        return result / summarizers.size();
    }

    public Float checkTruthType4(List<List<Double>> dataFirst, List<List<Double>> dataSecond, List<Label> summarizers) {
        float result = 0.0F;

        for(int i=0; i!= summarizers.size(); i++){
            System.out.println(getIter());
            setIter(getIter()+1);
            List<Double> data = new ArrayList<>(dataFirst.get(i));
            data.retainAll(dataSecond.get(i));
            summarizers.get(i).getLinguisticVariable().getMembershipFunction(summarizers.get(i).getSetName()).setValues(data);
            result += summarizers.get(i).getLinguisticVariable().getMembershipFunction(summarizers.get(i).getSetName()).getImprecision();
        }

        return 1 - result/summarizers.size();
    }

}
