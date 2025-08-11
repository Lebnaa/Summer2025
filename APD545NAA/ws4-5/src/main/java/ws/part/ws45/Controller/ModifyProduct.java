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

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ws.part.ws45.Model.Inventory;
import ws.part.ws45.Model.Part;
import ws.part.ws45.Model.Product;

public class ModifyProduct {

    @FXML
    private TextField idField, nameField, stockField, priceField, minField, maxField, searchPartField;

    @FXML
    private TableView<Part> allPartsTable;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partStockColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;
    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> associatedPartStockColumn;
    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    private Product selectedProduct;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public void setProduct(Product product) {
        selectedProduct = product;
        populateFields(product);
    }

    private void populateFields(Product product) {
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        stockField.setText(String.valueOf(product.getStock()));
        priceField.setText(String.valueOf(product.getPrice()));
        minField.setText(String.valueOf(product.getMin()));
        maxField.setText(String.valueOf(product.getMax()));

        associatedParts.setAll(product.getAllAssociatedParts());
        associatedPartsTable.setItems(associatedParts);
    }

    @FXML
    public void initialize() {

        partIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partStockColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        associatedPartIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        associatedPartNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        associatedPartStockColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        associatedPartPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        allPartsTable.setItems(Inventory.getAllParts());
        associatedPartsTable.setItems(associatedParts);
    }

    @FXML
    void onSearchPart(ActionEvent event) {
        String query = searchPartField.getText().trim();

        if (query.isEmpty()) {
            allPartsTable.setItems(Inventory.getAllParts()); // Reset if empty search
            return;
        }

        try {
            int partId = Integer.parseInt(query);
            Part part = Inventory.searchPartByID(partId);
            if (part != null) {
                allPartsTable.getItems().setAll(part);
            } else {
                showError("No Results", "No part found with the given ID.");
            }
        } catch (NumberFormatException e) {
            ObservableList<Part> results = Inventory.searchPartByName(query);
            if (!results.isEmpty()) {
                allPartsTable.setItems(results);
            } else {
                showError("No Results", "No parts found with the given name.");
            }
        }
    }

    @FXML
    void onAddPart(ActionEvent event) {
        Part selected = allPartsTable.getSelectionModel().getSelectedItem();
        if (selected != null && !associatedParts.contains(selected)) {
            associatedParts.add(selected);
        } else {
            showError("Selection Error", "Please select a valid part to add.");
        }
    }

    @FXML
    void onRemoveAssociatedPart(ActionEvent event) {
        Part selected = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            associatedParts.remove(selected);
        } else {
            showError("Selection Error", "Please select a valid part to remove.");
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());

            if (min > max) {
                showError("Invalid Input", "Minimum stock cannot be greater than maximum stock.");
                return;
            }
            if (stock < min || stock > max) {
                showError("Invalid Input", "Stock must be between min and max.");
                return;
            }

            Product newProduct = new Product(id, name, price, stock, min, max);
            newProduct.getAllAssociatedParts().setAll(associatedParts);

            int index = Inventory.getAllProducts().indexOf(selectedProduct);
            Inventory.updateProduct(index, newProduct);

            closeWindow();

        } catch (Exception e) {
            showError("Error Saving Product", "Please ensure all fields are filled correctly.");
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
}
