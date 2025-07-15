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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.assignment45.Model.InHouse;
import org.example.assignment45.Model.Inventory;
import org.example.assignment45.Model.Outsourced;
import org.example.assignment45.Model.Part;

public class AddPart {

    @FXML private Label partMachineOrCompanyLabel;
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outsourcedRadio;
    @FXML private ToggleGroup sourceToggleGroup;
    @FXML private TextField partIdField;
    @FXML private TextField partNameField;
    @FXML private TextField partInvField;
    @FXML private TextField partPriceField;
    @FXML private TextField partMaxField;
    @FXML private TextField partMinField;
    @FXML private TextField partMachineOrCompanyField;

    @FXML
    private void initialize() {
        inHouseRadio.setSelected(true);
        partMachineOrCompanyLabel.setText("Machine ID");
    }

    @FXML
    void onInHouseSelected(ActionEvent event) {
        partMachineOrCompanyLabel.setText("Machine ID");
    }

    @FXML
    void onOutsourcedSelected(ActionEvent event) {
        partMachineOrCompanyLabel.setText("Company Name");
    }

    @FXML
    void onSavePart(ActionEvent event) {
        try {
            int id = generateUniquePartId(); // You need a unique ID generator
            String name = partNameField.getText();
            double price = Double.parseDouble(partPriceField.getText());
            int stock = Integer.parseInt(partInvField.getText());
            int min = Integer.parseInt(partMinField.getText());
            int max = Integer.parseInt(partMaxField.getText());

            if (min > max) {
                showAlert("Min must be less than Max");
                return;
            }

            if (stock < min || stock > max) {
                showAlert("Inventory must be between Min and Max");
                return;
            }

            if (inHouseRadio.isSelected()) {
                int machineId = Integer.parseInt(partMachineOrCompanyField.getText());
                InHouse newPart = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(newPart);
            } else {
                String companyName = partMachineOrCompanyField.getText();
                Outsourced newPart = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart(newPart);
            }

            closeWindow();

        } catch (NumberFormatException e) {
            showAlert("Please enter valid values in all fields");
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        closeWindow();
    }

    private int generateUniquePartId() {
        return Inventory.getAllParts().size() + 1;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) partNameField.getScene().getWindow();
        stage.close();
    }
}
