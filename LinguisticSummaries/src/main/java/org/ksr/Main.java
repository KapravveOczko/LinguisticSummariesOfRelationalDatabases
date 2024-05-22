package org.ksr;
import org.ksr.Assets.*;
import org.ksr.DataController.DatabaseConnector;
import org.ksr.DataController.JsonConnector;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
//        Gui gui = new Gui();
//        gui.launchGui();


        String url = "jdbc:postgresql://localhost:5432/ksr";
        String user = "postgres";
        String password = "pass";

        DatabaseConnector db = new DatabaseConnector(url, user, password);
//        db.queryDataTable("test_data");

//        ---saving to json---------------------------------------------------------
//        JsonConnector jc = new JsonConnector();
//        LinguisticVariable toSave = new WaveSpeed();
//
//        jc.saveAllFuzzySetsToFolder(toSave.getFuzzySets(), "WaveSpeed");
//        --------------------------------------------------------------
        Assets assets = new Assets();

    }
}
