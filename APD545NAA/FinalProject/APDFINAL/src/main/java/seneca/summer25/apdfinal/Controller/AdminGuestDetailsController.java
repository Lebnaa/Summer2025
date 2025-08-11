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
import seneca.summer25.apdfinal.Utils.DatabaseUtil;

import java.io.IOException;
import java.sql.*;

public class AdminGuestDetailsController {

    @FXML
    private TextArea guestDetailsArea;

    @FXML
    private Button checkOutButton;

    @FXML
    private Button backButton;

    private int guestId = -1;
    private int reservationId = -1;
    private String guestName = "";
    private String roomType = "";
    private double discount = 0.0;

    public void setGuestDetails(String guestDetails, int guestId, String roomType) {
        this.guestId = guestId;
        this.roomType = roomType;
        this.guestName = extractNameFromDetails(guestDetails);
        guestDetailsArea.setText(guestDetails);

        // Fetch the latest reservation ID for this guest
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT ReservationID FROM Reservation WHERE GuestID = ? ORDER BY ReservationID DESC LIMIT 1";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, guestId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                reservationId = rs.getInt("ReservationID");
            }
        } catch (SQLException e) {
            showAlert("Failed to retrieve reservation ID.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCheckOut(ActionEvent event) {
        if (guestId == -1 || reservationId == -1) {
            showAlert("Missing guest or reservation ID.");
            return;
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            // 1. Get room price
            double roomPrice = 0.0;
            String roomSql = "SELECT Price FROM Room WHERE RoomType = ?";
            PreparedStatement priceStmt = conn.prepareStatement(roomSql);
            priceStmt.setString(1, roomType);
            ResultSet rsPrice = priceStmt.executeQuery();
            if (rsPrice.next()) roomPrice = rsPrice.getDouble("Price");

            // 2. Calculate tax and total
            double tax = roomPrice * 0.13;
            double total = (roomPrice - discount) * 1.13;

            // 3. Update guest status
            String updateSql = "UPDATE Guest SET Status = 'Checked Out' WHERE GuestID = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, guestId);
            updateStmt.executeUpdate();

            // 4. Insert billing
            String insertSql = "INSERT INTO Billing (ReservationID, Amount, Tax, TotalAmount, Discount) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement billingStmt = conn.prepareStatement(insertSql);
            billingStmt.setInt(1, reservationId);
            billingStmt.setDouble(2, roomPrice);
            billingStmt.setDouble(3, tax);
            billingStmt.setDouble(4, total);
            billingStmt.setDouble(5, discount);
            billingStmt.executeUpdate();

            // 5. Load billing view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/admin-billingview.fxml"));
            Parent root = loader.load();
            AdminBillingController billingController = loader.getController();
            billingController.setBillingDetails(guestName, reservationId, roomType); // Correct IDs

            Stage stage = (Stage) guestDetailsArea.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (SQLException | IOException e) {
            showAlert("Checkout failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        loadScene("/seneca/summer25/apdfinal/admin-searchview.fxml", event);
    }

    private String extractNameFromDetails(String details) {
        if (details != null && details.contains("Name:")) {
            int start = details.indexOf("Name:") + 5;
            int end = details.indexOf("\n", start);
            return end > start ? details.substring(start, end).trim() : details.substring(start).trim();
        }
        return "Guest";
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void loadScene(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Navigation failed.");
            e.printStackTrace();
        }
    }
}
