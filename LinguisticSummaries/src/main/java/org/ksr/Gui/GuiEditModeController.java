package org.ksr.Gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.ksr.Assets.Latitude;
import org.ksr.AssetsController.Assets;
import org.ksr.DataController.JsonConnector;
import org.ksr.FuzzyLib.FuzzySet.FuzzySet;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetConstants;
import org.ksr.FuzzyLib.FuzzySet.FuzzySetFactory;
import org.ksr.FuzzyLib.LinguisticSummary.Label;
import org.ksr.FuzzyLib.LinguisticVariable.LinguisticVariable;

public class GuiEditModeController {

    @FXML
    MenuItem exitEditMode;

    Assets assets = new Assets();
    JsonConnector jsonConnector = new JsonConnector();

    //----------creating membership----------//
    @FXML
    TextArea preview;
    @FXML
    Button createMembershipFunction;
    @FXML
    ChoiceBox<String> functionType;
    @FXML
    TextField name;
    @FXML
    TextField a;
    @FXML
    TextField b;
    @FXML
    TextField c;
    @FXML
    TextField d;
    @FXML
    TextArea membershipFunctionLog;

    FuzzySet createdFuzzySet;

    //----------adding membership----------//

    @FXML
    Button confirmAddingMembershipButton;
    @FXML
    ChoiceBox<LinguisticVariable> setToAddTo;
    @FXML
    ChoiceBox<Label> fuzzySetList;
    @FXML
    TextArea membershipFunctionAddLog;

    //----------deleting membership----------//
    @FXML
    ChoiceBox<String> choiceToDelete;
    @FXML
    ChoiceBox<String> choiceToDeleteFrom;
    @FXML
    Button deleteButton;
    @FXML
    TextArea membershipFunctionDeleteLog;

    //

    @FXML
    public void initialize() {
        membershipFunctionAddLog.setWrapText(true);
        membershipFunctionLog.setWrapText(true);
        membershipFunctionDeleteLog.setWrapText(true);
        initializeCreateView();
        initializeAddView();
    }

    @FXML
    private void handleExitEditModeClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Stage previousStage = (Stage) createMembershipFunction.getScene().getWindow();
            previousStage.close();

            stage.setTitle("LinguisticSummaries");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------creating membership----------//



    public void initializeCreateView(){
        setTypes();

        functionType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    membershipFunctionLog.setText("Wybrano: " + newValue);
                    updatePreview(functionType.getValue());
                }
            }
        });


    }

    public void setTypes(){
        functionType.getItems().addAll("TRIANGULAR","TRAPEZOIDAL","GAUSSIAN");
    }

    private void updatePreview(String newValue) {
        if (newValue.equals("TRIANGULAR")){
            newValue = "        \n" +
                    "if (x >= a && x <= b) {\n" +
                    "            return (x - a) / (b - a);\n" +
                    "        } else if (x > b && x <= c) {\n" +
                    "            return (c - x) / (c - b);\n" +
                    "        } else {\n" +
                    "            return 0.0;\n" +
                    "        }";
        } else if (newValue.equals("TRAPEZOIDAL")) {
            newValue = "        \n" +
                    "if (x >= a && x < b) {\n" +
                    "            return (x - a) / (b - a);\n" +
                    "        } else if (x >= b && x <= c) {\n" +
                    "            return 1.0;\n" +
                    "        } else if (x > c && x <= d) {\n" +
                    "            return (d - x) / (d - c);\n" +
                    "        } else {\n" +
                    "            return 0.0;\n" +
                    "        }";
        } else if (newValue.equals("GAUSSIAN")) {
            newValue = "\nexp(-0.5 * pow((x - a) / b, 2)";
        }
        preview.setText(newValue);
    }

    public void generateMembershipFunction() throws Exception{

        if(functionType.getValue().equals("TRIANGULAR")){
            try {
                createdFuzzySet = FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRIANGULAR,name.getText(), Double.parseDouble(a.getText()),Double.parseDouble(b.getText()),Double.parseDouble(c.getText()));
                membershipFunctionLog.clear();
                membershipFunctionLog.setText("membership function added to cash");
                System.out.println(createdFuzzySet);
                Label cretedLabel = new Label(createdFuzzySet.getName(), null);
                fuzzySetList.getItems().add(cretedLabel);
            }
            catch (Exception e){
                membershipFunctionLog.clear();
                membershipFunctionLog.setText("a, b or c values are not numbers");
            }
        }
        else if(functionType.getValue().equals("TRAPEZOIDAL")){
            try {
                createdFuzzySet = FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL,name.getText(), Double.parseDouble(a.getText()),Double.parseDouble(b.getText()),Double.parseDouble(c.getText()),Double.parseDouble(d.getText()));
                membershipFunctionLog.clear();
                membershipFunctionLog.setText("membership function added to cash");
                System.out.println(createdFuzzySet);
                Label cretedLabel = new Label(createdFuzzySet.getName(), null);
                fuzzySetList.getItems().add(cretedLabel);
            }
            catch (Exception e){
                membershipFunctionLog.clear();
                membershipFunctionLog.setText("a, b, c or d values are not numbers");
            }
        }
        else {
            try {
                createdFuzzySet = FuzzySetFactory.createMembershipFunction(FuzzySetConstants.GAUSSIAN,name.getText(), Double.parseDouble(a.getText()),Double.parseDouble(b.getText()));
                membershipFunctionLog.clear();
                membershipFunctionLog.setText("membership function added to cash");
                System.out.println(createdFuzzySet);
                Label cretedLabel = new Label(createdFuzzySet.getName(), null);
                fuzzySetList.getItems().add(cretedLabel);
            }
            catch (Exception e){
                membershipFunctionLog.clear();
                membershipFunctionLog.setText("a or b values are not numbers");
            }
        }


    }

    //----------adding membership----------//

    public void initializeAddView(){
        setToAddTo.getItems().addAll(assets.getVariables());
        setToAddTo.getItems().addAll(assets.getQuantifiers());
        fuzzySetList.getItems().addAll(assets.getAllVariables());
    }

    public void addMembershipFunction() {
        try {
            if(fuzzySetList.getValue().getSetName().equals(createdFuzzySet.getName())){
                jsonConnector.saveAllFuzzySetToFolder(createdFuzzySet, convertToPath(setToAddTo.getValue().getName()));
            }else {
                FuzzySet fuzzySet = fuzzySetList.getValue().getLinguisticVariable().getMembershipFunction(fuzzySetList.getValue().getSetName());
                jsonConnector.saveAllFuzzySetToFolder(fuzzySet, convertToPath(setToAddTo.getValue().getName()));
            }
            membershipFunctionAddLog.setText("Membership function added successfully");
        } catch (Exception e) {
            membershipFunctionAddLog.setText("Unable to proceed: " + e.getMessage());
        }
    }

    public String convertToPath(String name){

        return switch (name) {
            case "sea_bottom_temperature" -> "BottomTemperature";
            case "absolute_quantifiers" -> "AbsoluteQuantifiers";
            case "sea_bottom_salinity" -> "BottomSalinity";
            case "mixed_layer_depth" -> "Depth";
            case "latitude" -> "Latitude";
            case "longitude" -> "Longitude";
            case "relative_quantifiers" -> "RelativeQuantifiers";
            case "sea_surface_salinity" -> "SurfaceSalinity";
            case "sea_surface_temperature" -> "SurfaceTemperature";
            case "mean_wave_direction" -> "WaveDirection";
            case "mean_wave_period" -> "WaveFrequency";
            case "significant_wave_height" -> "WaveHeight";
            case "sea_surface_velocity" -> "WaveSpeed";
            default -> "";
        };
    }


    //----------deleting membership----------//
    public void deleteMembershipFunction(){


    }


}
