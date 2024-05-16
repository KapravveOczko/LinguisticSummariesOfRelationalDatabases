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

public class GuiEditModeController {

    @FXML
    MenuItem exitEditMode;

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

    //----------adding membership----------//

    @FXML
    Button confirmAddingMembershipButton;
    @FXML
    ChoiceBox<String> setToAddTo;
    @FXML
    ChoiceBox<String> membershipFunction;

    //----------deleting membership----------//
    @FXML
    ChoiceBox<String> choiceToDelete;
    @FXML
    ChoiceBox<String> choiceToDeleteFrom;
    @FXML
    Button deleteButton;

    //

    @FXML
    public void initialize() {
        setTypes();
        foo();

        functionType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updatePreview(newValue);
            }
        });
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

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------creating membership----------//

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
            newValue = "\nexp(-0.5 * Math.pow((x - a) / b, 2)";
        }
        preview.setText(newValue);
    }

    //----------deleting membership----------//
    public void foo(){
        setToAddTo.getItems().addAll("SurfaceSalinity");
        choiceToDeleteFrom.getItems().addAll("SurfaceSalinity");
        choiceToDelete.getItems().addAll("salty");
        membershipFunction.getItems().addAll("newFunction");

    }


}
