package org.ksr;
import org.ksr.Db.DatabaseConnector;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Gui gui = new Gui();
//        gui.launchGui();


        String url = "jdbc:postgresql://localhost:5432/ksr";
        String user = "postgres";
        String password = "pass";

        DatabaseConnector db = new DatabaseConnector(url, user, password);
//        db.queryDataTable("test_data");

        ArrayList<Double> data = db.getDataFromColumn("test_small_data", "sea_bottom_temperature");

        for(int i=0; i!= data.size(); i++){
            System.out.println(data.get(i));
        }

    }
}
