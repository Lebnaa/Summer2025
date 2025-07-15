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

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.assignment45.Model.Inventory;
import org.example.assignment45.Model.Part;
import org.example.assignment45.Model.Product;

public class AddProduct {

    @FXML
    private TextField idField, nameField, stockField, priceField, minField, maxField, searchPartField;

    @FXML
    private TableView<Part> allPartsTable;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> allPartsIdColumn, associatedPartsIdColumn;

    @FXML
    private TableColumn<Part, String> allPartsNameColumn, associatedPartsNameColumn;

    @FXML
    private TableColumn<Part, Double> allPartsPriceColumn, associatedPartsPriceColumn;

    @FXML
    private TableColumn<Part, Integer> allPartsStockColumn, associatedPartsStockColumn;

    private Product tempProduct;

    @FXML
    public void initialize() {

        allPartsIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        allPartsNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        allPartsPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        allPartsStockColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());

        associatedPartsIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        associatedPartsNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        associatedPartsPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        associatedPartsStockColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());

        tempProduct = new Product(0, "", 0.0, 0, 0, 0);
        associatedPartsTable.setItems(tempProduct.getAllAssociatedParts());
        allPartsTable.setItems(Inventory.getAllParts());
    }

    @FXML
    void onSearchPart(ActionEvent event) {
        String query = searchPartField.getText().trim();
        ObservableList<Part> results = FXCollections.observableArrayList();

        try {
            int partId = Integer.parseInt(query);
            Part part = Inventory.searchPartByID(partId);
            if (part != null) {
                results.add(part);
            }
        } catch (NumberFormatException e) {
            results = Inventory.searchPartByName(query);
        }

        if (results.isEmpty()) {
            showInfo("Search Result", "No parts found matching: " + query);
        }

        allPartsTable.setItems(results);
    }

    @FXML
    void onAddPart(ActionEvent event) {
        Part selected = allPartsTable.getSelectionModel().getSelectedItem();
        if (selected != null && !tempProduct.getAllAssociatedParts().contains(selected)) {
            tempProduct.addAssociatedPart(selected);
        }
    }

    @FXML
    void onRemoveAssociatedPart(ActionEvent event) {
        Part selected = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            tempProduct.deleteAssociatedPart(selected);
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        try {
            int id = Inventory.getAllProducts().size() + 1;
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());

            if (min > max) {
                showError("Validation Error", "Min must be less than Max.");
                return;
            }

            if (stock < min || stock > max) {
                showError("Validation Error", "Inventory must be between Min and Max.");
                return;
            }

            Product newProduct = new Product(id, name, price, stock, min, max);
            for (Part part : tempProduct.getAllAssociatedParts()) {
                newProduct.addAssociatedPart(part);
            }

            Inventory.addProduct(newProduct);
            closeWindow();

        } catch (Exception e) {
            showError("Error Saving Product", "Please check all fields are filled correctly.");
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
