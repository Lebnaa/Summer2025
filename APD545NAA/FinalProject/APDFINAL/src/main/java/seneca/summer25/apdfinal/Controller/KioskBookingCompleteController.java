package seneca.summer25.apdfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class KioskBookingCompleteController {

    @FXML
    private Button finishButton;

    @FXML
    private void handleFinish(ActionEvent event) {
        System.out.println("Finish button clicked.");
        showAlert("Thank you for booking with us!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/welcome-page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) finishButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Could not load welcome page.");
            e.printStackTrace();
        }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Navigation Error");
        alert.setHeaderText("FXML Load Failure");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
