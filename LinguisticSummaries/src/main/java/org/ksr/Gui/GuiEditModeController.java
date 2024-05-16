package org.ksr.Gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiEditModeController {

    @FXML
    MenuItem exitEditMode;
    @FXML
    Button createMembershipFunction;

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
}
