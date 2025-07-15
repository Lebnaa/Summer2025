/**********************************************
 Workshop #4-5
 Course: APD545
 Last Name: Patel
 First Name: Srujal
 ID: 182573212
 Section: NBB
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date: 16th Mar, 2025
 **********************************************/

package org.example.assignment45.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Login {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize() {

        usernameField.setTooltip(new Tooltip("Enter your username"));
        passwordField.setTooltip(new Tooltip("Enter your password"));
        usernameField.requestFocus();
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Empty Fields", "Username and password are required!");
            return;
        }

        if (username.equals("srujal") && password.equals("srujal123")) {
            try {
                Stage stage = (Stage) usernameField.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/org/example/assignment45/main-page.fxml"));

                stage.setTitle("Main Page");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Loading Error", "Failed to load Main Page.\n" + e.getMessage());
                e.printStackTrace();
            }

        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password!");

            usernameField.clear();
            passwordField.clear();
            usernameField.requestFocus();
        }
    }

    @FXML
    void handleCancel(ActionEvent event) {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header
        alert.setContentText(message);
        alert.showAndWait();
    }
}
