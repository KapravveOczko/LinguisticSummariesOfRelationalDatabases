package org.ksr.Gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ksr.AssetsController.Assets;
import org.ksr.Assets.None;
import org.ksr.DataController.DatabaseConnector;
import org.ksr.FuzzyLib.LinguisticSummary.Label;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;
import org.ksr.FuzzyLib.LinguisticSummary.LinguisticSummary;
import org.ksr.FuzzyLib.LinguisticSummary.LinguisticSummaryTwoSubject;

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
    private TextArea output;

    @FXML
    public void initialize() {
        initializeSummarizers();
        initializeQualifier();
        initializeQuantifiers();
        initializeSubjects();
        output.setWrapText(true);
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
        Label none = new Label("none", null);
        qualifierChoice.getItems().addAll(assets.getAllVariables());
        qualifierChoice.getItems().add(none);
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
    public void handleGenerateLSButtonClick() throws Exception{
        output.clear();
        System.out.println("Generate linguistic summary button clicked!");
        DatabaseConnector db = new DatabaseConnector("jdbc:postgresql://localhost:5432/ksr", "postgres", "");
        List<String> summaries = new ArrayList<>();

        Label none = new Label("none", null);
        None noneNone = new None("none");

        try {

            Label quantifier = quantifierChoice.getValue();
            Label qualifier = null;
            if(!qualifierChoice.getValue().getSetName().equals(none.getSetName())){
                quantifier = qualifierChoice.getValue();
            }

            List<LinguisticVariable> summarizers = new ArrayList<>();
            if(!summarizerChoice1.getValue().getName().equals(noneNone.getName())){
                summarizers.add(summarizerChoice1.getValue());
            }
            if(!summarizerChoice2.getValue().getName().equals(noneNone.getName())){
                summarizers.add(summarizerChoice2.getValue());
            }
            if(!summarizerChoice3.getValue().getName().equals(noneNone.getName())){
                summarizers.add(summarizerChoice3.getValue());
            }

            if(!subjectChoice1.getValue().equals("none") && subjectChoice2.getValue().equals("none")){
                LinguisticSummary testSummary = new LinguisticSummary(db, subjectChoice1.getValue(), qualifier, summarizers, quantifier);
                summaries = testSummary.createLinguisticSummary();
            }
            else if(!subjectChoice1.getValue().equals("none") && !subjectChoice2.getValue().equals("none") && !subjectChoice1.getValue().equals(subjectChoice2.getValue())) {
                LinguisticSummaryTwoSubject testSummaryTwoSubject = new LinguisticSummaryTwoSubject(db, subjectChoice1.getValue(), subjectChoice2.getValue(), qualifier, summarizers, quantifier);
                summaries = testSummaryTwoSubject.createLinguisticSummaryTwoSubject();
            }

            for(String summary : summaries){
                System.out.println(summary);
                output.appendText(summary);
                output.appendText("\n\n");
            }
        }catch (Exception e){
            output.appendText("unable to generate summaries for chosen values \n\n please check your choices and remember: \n set unused fields to 'none' \n while using one summarizer put your choice in first field \n\n" + e.getMessage());
        }

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
