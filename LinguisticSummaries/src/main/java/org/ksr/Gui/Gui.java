package org.ksr.Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Gui extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
        BorderPane mainPane = loader.load();
        Scene scene = new Scene(mainPane);
        stage.setTitle("LinguisticSummaries");
        stage.setScene(scene);
        stage.show();
    }

    public void launchGui(){
        launch();
    }

}
