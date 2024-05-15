package org.ksr.Gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class GuiController {

    @FXML
    private Button buttonGenerateLS;
    @FXML
    private Accordion menuAccordion;

    @FXML
    public void initialize() {
        addPane("xd");
    }

    @FXML
    public void handleGenerateLSButtonClick() {
        System.out.println("Generate linguistic summary button clicked!");
        addPane("xd");
    }

    @FXML
    public void addPane(String name){
        TitledPane tPane = new TitledPane();
        CheckBox checkBox = new CheckBox("apply");
        VBox content = new VBox(checkBox);
        tPane.setText(name);
        tPane.setContent(content);

        this.menuAccordion.getPanes().add(tPane);
    }



}
