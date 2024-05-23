package org.ksr;
import org.ksr.Assets.*;
import org.ksr.DataController.DatabaseConnector;
import org.ksr.FuzzyLib.LinguisticSummary.Label;
import org.ksr.FuzzyLib.LinguisticSummary.LinguisticSummary;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        Gui gui = new Gui();
//        gui.launchGui();

        DatabaseConnector db = new DatabaseConnector("jdbc:postgresql://localhost:5432/ksr", "postgres", "pass");


        Assets assets = new Assets();
        Label qualifier = new Label("a few", assets.getVariable("relative_quantifiers"));
        List<LinguisticVariable> summarizers = new ArrayList<>();
        summarizers.add(assets.getVariable("longitude"));
        summarizers.add(assets.getVariable("significant_wave_height"));

        List<ArrayList<Double>> data = new ArrayList<>();
        for( LinguisticVariable summarizer : summarizers){
        data.add(db.getDataFromColumn("test_small_data", summarizer.getName()));
        }


//        summarizers.add(assets.getVariable("mixed_layer_depth"));
//        summarizers.add(assets.getVariable("mixed_layer_depth"));

        LinguisticSummary testSummary = new LinguisticSummary(qualifier, summarizers, qualifier);
        List<String> summaries = testSummary.createLinguisticSummary();
        for(String summary : summaries){
            System.out.println(summary);
        }

    }
}
