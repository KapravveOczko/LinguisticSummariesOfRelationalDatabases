package org.ksr.Gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ksr.AssetsController.Assets;
import org.ksr.Assets.None;
import org.ksr.FuzzyLib.LinguisticSummary.Label;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiMainViewController {

    Assets assets = new Assets();

    @FXML
    private Button buttonGenerateLS;
    @FXML
    private ChoiceBox<Label> quantifierChoice;
    
    @FXML
    private ChoiceBox<String> subjectChoice1;
    @FXML
    private ChoiceBox<String> subjectChoice2;
    
    @FXML
    private ChoiceBox<Label> qualifierChoice;


    @FXML
    private ChoiceBox<LinguisticVariable> summarizerChoice1;
    @FXML
    private ChoiceBox<LinguisticVariable> summarizerChoice2;
    @FXML
    private ChoiceBox<LinguisticVariable> summarizerChoice3;
    @FXML
    private MenuItem editButton;

    @FXML
    public void initialize() {
        initializeSummarizers();
        initializeQualifier();
        initializeQuantifiers();
        initializeSubjects();
    }

    @FXML
    public void initializeSubjects(){
        subjectChoice1.getItems().addAll("none", "test_data", "test_small_data", "test_full_data", "test_irland_data", "test_north_data", "test_south_data");
        subjectChoice2.getItems().addAll("none", "test_data", "test_small_data", "test_full_data", "test_irland_data", "test_north_data", "test_south_data");
    }

    @FXML
    public void initializeQuantifiers(){
        quantifierChoice.getItems().addAll(assets.getAllQuantifiers());
    }

    @FXML
    public void initializeQualifier(){
        qualifierChoice.getItems().addAll(assets.getAllVariables());
    }
    @FXML
    public void initializeSummarizers(){
        LinguisticVariable none = new None("none");

        summarizerChoice1.getItems().addAll(assets.getVariables());
        summarizerChoice1.getItems().add(none);

        summarizerChoice2.getItems().addAll(assets.getVariables());
        summarizerChoice2.getItems().add(none);

        summarizerChoice3.getItems().addAll(assets.getVariables());
        summarizerChoice3.getItems().add(none);
    }

    @FXML
    public void handleGenerateLSButtonClick() {
        System.out.println("Generate linguistic summary button clicked!");
    }

    @FXML
    private void handleEditButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditMode.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Stage previousStage = (Stage) buttonGenerateLS.getScene().getWindow();
            previousStage.close();

            stage.setTitle("EditMode");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
