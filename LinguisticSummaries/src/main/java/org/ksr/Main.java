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
        Label quantifier = new Label("about one third", assets.getVariable("relative_quantifiers"));
        Label qualifier = new Label("east fo Ireland", assets.getVariable("longitude"));

        List<LinguisticVariable> summarizers = new ArrayList<>();
        summarizers.add(assets.getVariable("sea_surface_temperature"));
        summarizers.add(assets.getVariable("significant_wave_height"));
        summarizers.add(assets.getVariable("mixed_layer_depth"));

        LinguisticSummary testSummary = new LinguisticSummary(qualifier, summarizers, quantifier);
        List<String> summaries = testSummary.createLinguisticSummary();
        for(String summary : summaries){
            System.out.println(summary);
        }

    }
}
