package org.ksr;
import org.ksr.Assets.*;
import org.ksr.DataController.DatabaseConnector;
import org.ksr.DataController.JsonConnector;
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


        Assets assets = new Assets();
        Label qualifier = new Label("a few", assets.getVariable("relative_quantifiers"));
        List<Label> summarizers = new ArrayList<>();
        summarizers.add(new Label("calm seas", assets.getVariable("significant_wave_height")));
        summarizers.add(new Label("east", assets.getVariable("mean_wave_direction")));
        summarizers.add(new Label("surface", assets.getVariable("mixed_layer_depth")));

        LinguisticSummary testSummary = new LinguisticSummary(qualifier, summarizers, qualifier);
        List<String> summaries = testSummary.createLinguisticSummary();
        for(String summary : summaries){
            System.out.println(summary);
        }

    }
}
