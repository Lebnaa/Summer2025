package seneca.summer25.apdfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class KioskGuestDetailsController {

    @FXML
    private TextField adultsField;

    @FXML
    private TextField childrenField;

    @FXML
    private DatePicker checkInDatePicker;

    @FXML
    private DatePicker checkOutDatePicker;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private Button nextButton;

    private String guestPhone = "";

    @FXML
    private void handleNext(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String adultsText = adultsField.getText();
        String childrenText = childrenField.getText();

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() ||
                adultsText.isEmpty() || childrenText.isEmpty() ||
                checkInDatePicker.getValue() == null || checkOutDatePicker.getValue() == null) {
            showAlert("Please fill in all fields.");
            return;
        }

        try {
            int numAdults = Integer.parseInt(adultsText);
            int numChildren = Integer.parseInt(childrenText);

            if (numAdults <= 0) {
                showAlert("Number of adults must be at least 1.");
                return;
            }

            String checkInDate = checkInDatePicker.getValue().toString();
            String checkOutDate = checkOutDatePicker.getValue().toString();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/kiosk-roomselection.fxml"));
            Parent root = loader.load();

            KioskRoomSelectionController controller = loader.getController();
            controller.setGuestDetails(
                    numAdults, numChildren, checkInDate, checkOutDate,
                    name, email, address, guestPhone
            );

            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (NumberFormatException e) {
            showAlert("Please enter valid numbers for adults and children.");
        } catch (IOException e) {
            showError("Could not load room selection screen.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/kiosk-guestinfo.fxml"));
            Parent root = loader.load();

            KioskGuestInfoFormController infoController = loader.getController();
            String name = nameField.getText();
            int guestCount = 1;

            try {
                int adults = Integer.parseInt(adultsField.getText());
                int children = Integer.parseInt(childrenField.getText());
                guestCount = adults + children;
            } catch (Exception ignored) {}

            infoController.prefillGuestInfo(name, guestPhone, guestCount);

            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            showError("Failed to load previous screen.");
            e.printStackTrace();
        }
    }

    //  Receives values from guest-info form
    public void prefillGuestInfo(String name, String phone, int guestCount) {
        nameField.setText(name);
        guestPhone = phone;
        adultsField.setText(String.valueOf(guestCount));
        childrenField.setText("0");
        emailField.setText("");
        addressField.setText("");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Navigation Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
