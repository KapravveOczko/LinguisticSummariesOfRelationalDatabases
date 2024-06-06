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
    ChoiceBox<LinguisticVariable> choiceToDeleteFrom;
    @FXML
    ChoiceBox<FuzzySet> choiceToDelete;
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
        initializeDeleteView();
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

    //----------creating membership----------//



    public void initializeCreateView(){
        setTypes();

        functionType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    membershipFunctionLog.setText("chosen: " + newValue);
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

    public void generateMembershipFunction() throws Exception {
        try {
            double aValue = Double.parseDouble(a.getText());
            double bValue = Double.parseDouble(b.getText());
            double cValue = Double.parseDouble(c.getText());
            double dValue = d.getText().isEmpty() ? 0 : Double.parseDouble(d.getText());

            if (functionType.getValue().equals("TRIANGULAR")) {
                if (aValue == bValue || bValue == cValue || aValue == cValue) {
                    membershipFunctionLog.setText("Values of a, b, and c must be distinct for TRIANGULAR function.");
                    return;
                }
                createdFuzzySet = FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRIANGULAR, name.getText(), aValue, bValue, cValue);
            } else if (functionType.getValue().equals("TRAPEZOIDAL")) {
                if (aValue == bValue || bValue == cValue || cValue == dValue || aValue == dValue) {
                    membershipFunctionLog.setText("Values of a, b, c, and d must be distinct for TRAPEZOIDAL function.");
                    return;
                }
                createdFuzzySet = FuzzySetFactory.createMembershipFunction(FuzzySetConstants.TRAPEZOIDAL, name.getText(), aValue, bValue, cValue, dValue);
            } else {
                if (aValue == bValue) {
                    membershipFunctionLog.setText("Values of a and b must be distinct for GAUSSIAN function.");
                    return;
                }
                createdFuzzySet = FuzzySetFactory.createMembershipFunction(FuzzySetConstants.GAUSSIAN, name.getText(), aValue, bValue);
            }

            membershipFunctionLog.clear();
            membershipFunctionLog.setText("Membership function added to cache");
            System.out.println(createdFuzzySet);
            Label createdLabel = new Label(createdFuzzySet.getName(), null);
            fuzzySetList.getItems().add(createdLabel);

        } catch (NumberFormatException e) {
            membershipFunctionLog.clear();
            membershipFunctionLog.setText("a, b, c, or d values are not numbers");
        } catch (Exception e) {
            membershipFunctionLog.clear();
            membershipFunctionLog.setText("An error occurred: " + e.getMessage());
            e.printStackTrace();
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


    //----------deleting membership----------//

    public void initializeDeleteView() {
        choiceToDeleteFrom.getItems().addAll(assets.getVariables());
        choiceToDeleteFrom.getItems().addAll(assets.getQuantifiers());

        choiceToDeleteFrom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LinguisticVariable>() {
            @Override
            public void changed(ObservableValue<? extends LinguisticVariable> observable, LinguisticVariable oldValue, LinguisticVariable newValue) {
                if (newValue != null) {
                    membershipFunctionDeleteLog.setText("chosen: " + newValue.getName());
                    try {
                        choiceToDelete.getItems().clear();
                        choiceToDelete.getItems().addAll(jsonConnector.loadAllFuzzySetsFromFolder(convertToPath(newValue.getName())));
                    } catch (IOException e) {
                        membershipFunctionDeleteLog.setText("An error occurred while loading fuzzy sets: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void deleteMembershipFunction() throws Exception{
        try {
            jsonConnector.deleteFile(convertToPath(choiceToDeleteFrom.getValue().getName()), choiceToDelete.getValue().getName() + ".json");
            membershipFunctionDeleteLog.clear();
            membershipFunctionDeleteLog.setText("successfully deleted membership function");
        } catch (Exception e) {
            membershipFunctionDeleteLog.clear();
            membershipFunctionDeleteLog.setText("unable to proceed: " + e.getMessage());
        }

    }


}
