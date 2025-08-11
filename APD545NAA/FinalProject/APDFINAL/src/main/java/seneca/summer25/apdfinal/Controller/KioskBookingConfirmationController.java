package seneca.summer25.apdfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class KioskBookingConfirmationController {

    @FXML
    private TextArea bookingSummaryArea;

    @FXML
    private Button confirmButton;

    @FXML
    private Button backButton;

    public void setBookingSummary(String summary) {
        bookingSummaryArea.setText(summary);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        loadScene("/seneca/summer25/apdfinal/kiosk-guestdetails.fxml", event);
    }

    @FXML
    private void handleConfirm(ActionEvent event) {
        showAlert("Booking confirmed. Enjoy your stay!");
        loadScene("/seneca/summer25/apdfinal/kiosk-bookingcomplete.fxml", event);
    }

    private void loadScene(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Failed to load screen: " + fxmlPath);
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
