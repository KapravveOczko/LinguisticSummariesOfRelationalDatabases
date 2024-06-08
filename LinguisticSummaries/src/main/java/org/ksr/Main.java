package org.ksr;
import org.ksr.AssetsController.Assets;
import org.ksr.DataController.DatabaseConnector;
import org.ksr.FuzzyLib.LinguisticSummary.Label;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;
import org.ksr.Gui.Gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Gui gui = new Gui();
        gui.launchGui();

    }
}
