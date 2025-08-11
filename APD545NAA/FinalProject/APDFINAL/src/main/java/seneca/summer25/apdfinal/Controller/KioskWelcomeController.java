package seneca.summer25.apdfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class KioskWelcomeController {

    @FXML
    private void handleStartBooking(ActionEvent event) {
        System.out.println("Start Booking button clicked.");
        showInfo("Redirecting to Guest Info Form...");
        loadScene("/seneca/summer25/apdfinal/kiosk-guestinfo.fxml", event);
    }

    @FXML
    private void handleAdminLogin(ActionEvent event) {
        System.out.println("Admin Login button clicked.");
        showInfo("Redirecting to Admin Login...");
        loadScene("/seneca/summer25/apdfinal/admin-loginview.fxml", event);
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadScene(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (loader.getLocation() == null) {
                throw new IllegalStateException("FXML file not found: " + fxmlPath);
            }
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException | IllegalStateException e) {
            showError("Failed to load: " + fxmlPath);
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Navigation Error");
        alert.setHeaderText("FXML Load Failure");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
