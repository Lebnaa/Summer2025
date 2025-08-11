/**********************************************
 Workshop #4-5
 Course: APD545
 Last Name: Noori
 First Name: Lebna
 ID: 157672205
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date: July/25
 **********************************************/

package ws.part.ws45.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ws.part.ws45.Model.InHouse;
import ws.part.ws45.Model.Inventory;
import ws.part.ws45.Model.Outsourced;
import ws.part.ws45.Model.Part;

public class ModifyPart {

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

    private Part selectedPart;

    public void setPart(Part part) {
        this.selectedPart = part;

        partIdField.setText(String.valueOf(part.getId()));
        partNameField.setText(part.getName());
        partInvField.setText(String.valueOf(part.getStock()));
        partPriceField.setText(String.valueOf(part.getPrice()));
        partMinField.setText(String.valueOf(part.getMin()));
        partMaxField.setText(String.valueOf(part.getMax()));

        if (part instanceof InHouse) {
            inHouseRadio.setSelected(true);
            partMachineOrCompanyLabel.setText("Machine ID");
            partMachineOrCompanyField.setText(String.valueOf(((InHouse) part).getMachineId()));
        } else if (part instanceof Outsourced) {
            outsourcedRadio.setSelected(true);
            partMachineOrCompanyLabel.setText("Company Name");
            partMachineOrCompanyField.setText(((Outsourced) part).getCompanyName());
        }
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
            int id = Integer.parseInt(partIdField.getText());
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

            Part updatedPart;
            if (inHouseRadio.isSelected()) {
                int machineId = Integer.parseInt(partMachineOrCompanyField.getText());
                updatedPart = new InHouse(id, name, price, stock, min, max, machineId);
            } else {
                String companyName = partMachineOrCompanyField.getText();
                updatedPart = new Outsourced(id, name, price, stock, min, max, companyName);
            }

            // Replace old part with updated part
            int index = Inventory.getAllParts().indexOf(selectedPart);
            Inventory.getAllParts().set(index, updatedPart);

            closeWindow();

        } catch (NumberFormatException e) {
            showAlert("Please enter valid values in all fields");
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        closeWindow();
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
