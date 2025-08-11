package seneca.summer25.apdfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class KioskGuestInfoFormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private Spinner<Integer> guestCountSpinner;

    @FXML
    public void initialize() {
        guestCountSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
    }

    @FXML
    private void handleNext(ActionEvent event) {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        int guestCount = guestCountSpinner.getValue();

        if (name.isEmpty() || phone.isEmpty()) {
            showError("Please fill in all required fields.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/kiosk-guestdetails.fxml"));
            Parent root = loader.load();

            KioskGuestDetailsController controller = loader.getController();
            controller.prefillGuestInfo(name, phone, guestCount);

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Could not load guest details screen.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        loadScene("/seneca/summer25/apdfinal/welcome-page.fxml", event);
    }

    private void loadScene(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Failed to load: " + fxmlPath);
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void prefillGuestInfo(String name, String phone, int guestCount) {
        nameField.setText(name);
        phoneField.setText(phone);
        guestCountSpinner.getValueFactory().setValue(guestCount);
    }
}
