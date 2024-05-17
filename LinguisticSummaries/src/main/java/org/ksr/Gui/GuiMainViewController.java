package org.ksr.Gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ksr.Assets.BottomSalinity;
import org.ksr.Assets.None;
import org.ksr.Assets.SurfaceSalinity;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

import java.io.IOException;

public class GuiMainViewController {

    @FXML
    private Button buttonGenerateLS;
    @FXML
    private ChoiceBox<LinguisticVariable> quantifierChoice;
    @FXML
    private ChoiceBox<LinguisticVariable> qualifierChoice;
    @FXML
    private ChoiceBox<LinguisticVariable> summarizer1;
    @FXML
    private ChoiceBox<LinguisticVariable> summarizer2;
    @FXML
    private ChoiceBox<LinguisticVariable> summarizer3;
    @FXML
    private MenuItem editButton;

    @FXML
    public void initialize() {
        initializeSummarizers();
        initializeQualifier();
        initializeQuantifiers();
    }

    @FXML
    public void initializeQuantifiers(){
        quantifierChoice.getItems().addAll(summarizer1.getItems());
    }

    @FXML
    public void initializeQualifier(){
        qualifierChoice.getItems().addAll(summarizer1.getItems());
    }
    @FXML
    public void initializeSummarizers(){
        LinguisticVariable bottomSalinity = new BottomSalinity("bottomSalinity");
        LinguisticVariable surfaceSalinity = new SurfaceSalinity("surfaceSalinity");
        LinguisticVariable none = new None("none");
        summarizer1.getItems().addAll(none, bottomSalinity, surfaceSalinity);
        summarizer2.getItems().addAll(summarizer1.getItems());
        summarizer3.getItems().addAll(summarizer1.getItems());

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
