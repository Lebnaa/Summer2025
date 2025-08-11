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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ws.part.ws45.Model.*;
import ws.part.ws45.Model.*;

import java.io.IOException;
import java.util.Optional;

public class MainPage {

    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInvCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInvCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField partSearchField;
    @FXML
    private TextField productSearchField;

    @FXML
    public void initialize() {

        Part part1 = new InHouse(1, "LipStick", 20.99, 100, 10, 500, 101);
        Part part2 = new InHouse(2, "Foundation", 90.00, 150, 20, 600, 102);
        Part part3 = new InHouse(3, "Hair Brush", 10.00, 200, 30, 700, 103);
        Part part4 = new Outsourced(4, "Nail Polish", 15.99, 20, 5, 50, "MAC");
        Part part5 = new Outsourced(5, "Powder", 25.50, 15, 3, 40, "L'Oreal");

        Product product1 = new Product(1, "Makeup Kit", 299.99, 5, 1, 10);
        Product product2 = new Product(2, "Everyday Essentials Set", 199.99, 8, 2, 15);
        Product product3 = new Product(3, "Hair & Beauty Combo", 99.99, 12, 5, 20);
        Product product4 = new Product(4, "Nail Care Package", 49.99, 25, 10, 50);
        Product product5 = new Product(5, "Professional Beauty Bundle", 149.99, 7, 3, 12);

        product1.addAssociatedPart(part4); // Wheel
        product1.addAssociatedPart(part5); // Seat
        product2.addAssociatedPart(part1); // Bolt
        product2.addAssociatedPart(part2); // Nut
        product3.addAssociatedPart(part3); // Screw
        product4.addAssociatedPart(part3); // Screw
        product5.addAssociatedPart(part4); // Wheel

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);

        partTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void onSearchPart(ActionEvent event) {
        String query = partSearchField.getText().trim();
        ObservableList<Part> searchResults = FXCollections.observableArrayList();

        if (query.isEmpty()) {
            partTable.setItems(Inventory.getAllParts()); // Reset table if search is empty
            return;
        }

        try {
            int partId = Integer.parseInt(query);
            Part part = Inventory.searchPartByID(partId);
            if (part != null) {
                searchResults.add(part);
            }
        } catch (NumberFormatException e) {
            searchResults = Inventory.searchPartByName(query);
        }

        if (searchResults.isEmpty()) {
            showAlert("No Match", "No parts found matching: " + query);
        } else {
            partTable.setItems(searchResults);
        }
    }

    @FXML
    void onSearchProduct(ActionEvent event) {
        String query = productSearchField.getText().trim();
        ObservableList<Product> searchResults = FXCollections.observableArrayList();

        if (query.isEmpty()) {
            productTable.setItems(Inventory.getAllProducts()); // Reset table if search is empty
            return;
        }

        try {
            int productId = Integer.parseInt(query);
            Product product = Inventory.searchProductByID(productId);
            if (product != null) {
                searchResults.add(product);
            }
        } catch (NumberFormatException e) {
            searchResults = Inventory.searchProductByName(query);
        }

        if (searchResults.isEmpty()) {
            showAlert("No Match", "No products found matching: " + query);
        } else {
            productTable.setItems(searchResults);
        }
    }

    @FXML
    void onAddPart(ActionEvent event) throws IOException {
        openWindow("/ws/part/ws45/add-part.fxml", "Add Part");
    }

    @FXML
    void onModifyPart(ActionEvent event) throws IOException {
        Part selected = partTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Part Selected", "Please select a part to modify.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ws/part/ws45/modify-part.fxml"));
        Parent root = loader.load();

        ModifyPart controller = loader.getController();
        controller.setPart(selected);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Modify Part");
        stage.show();
    }

    @FXML
    void onDeletePart(ActionEvent event) {
        Part selected = partTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Part Selected", "Please select a part to delete.");
            return;
        }

        boolean confirm = showConfirmation("Delete Part", "Are you sure you want to delete the selected part?");
        if (confirm) {
            boolean success = Inventory.deletePart(selected);
            if (success) {
                partTable.setItems(Inventory.getAllParts()); // Refresh table
            }
        }
    }

    @FXML
    void onAddProduct(ActionEvent event) throws IOException {
        openWindow("/ws/part/ws45/add-product.fxml", "Add Product");
    }

    @FXML
    void onModifyProduct(ActionEvent event) throws IOException {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Product Selected", "Please select a product to modify.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ws/part/ws45/modify-product.fxml"));
        Parent root = loader.load();

        ModifyProduct controller = loader.getController();
        controller.setProduct(selected);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Modify Product");
        stage.show();
    }


    @FXML
    void onDeleteProduct(ActionEvent event) {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Product Selected", "Please select a product to delete.");
            return;
        }

        if (!selected.getAllAssociatedParts().isEmpty()) {
            showAlert("Delete Error", "Cannot delete a product with associated parts. Remove them first.");
            return;
        }

        boolean confirm = showConfirmation("Delete Product", "Are you sure you want to delete the selected product?");
        if (confirm) {
            boolean success = Inventory.deleteProduct(selected);
            if (success) {
                productTable.setItems(Inventory.getAllProducts()); // Refresh table
            }
        }
    }

    @FXML
    void onExitApplication(ActionEvent event) {
        Stage stage = (Stage) partTable.getScene().getWindow();
        stage.close();
    }

    private void openWindow(String fxmlPath, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean showConfirmation(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
