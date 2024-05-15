package org.ksr.Gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class GuiController {

    @FXML
    private Button buttonGenerateLS;

    @FXML
    public void initialize() {
    }

    @FXML
    public void handleGenerateLSButtonClick() {
        System.out.println("Generate linguistic summary button clicked!");
    }

}
