package org.ksr;
import org.ksr.Assets.BottomSalinity;
import org.ksr.DataController.DatabaseConnector;
import org.ksr.DataController.JsonConnector;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        Gui gui = new Gui();
//        gui.launchGui();


        String url = "jdbc:postgresql://localhost:5432/ksr";
        String user = "postgres";
        String password = "pass";

        DatabaseConnector db = new DatabaseConnector(url, user, password);
//        db.queryDataTable("test_data");

//        ------------------------------------------------------------
        JsonConnector jc = new JsonConnector();
        LinguisticVariable bottomSalinity = new BottomSalinity("bottomSalinity");

        jc.saveAllFuzzySetsToFolder(bottomSalinity.getFuzzySets(), "BottomSalinity");
//        --------------------------------------------------------------
        LinguisticVariable bottomSalinityTest = new BottomSalinity("bottomSalinityTest");
        bottomSalinityTest.setFuzzySets(jc.loadAllFuzzySetsFromFolder("BottomSalinity"));
        jc.saveAllFuzzySetsToFolder(bottomSalinityTest.getFuzzySets(), "BottomSalinityTest");


    }
}
