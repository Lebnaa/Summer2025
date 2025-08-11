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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBillingController {

    @FXML
    private TextArea billingDetailsArea;

    @FXML
    private Button applyDiscountButton;

    @FXML
    private Button finalizeCheckOutButton;

    @FXML
    private Button backButton;

    private boolean discountApplied = false;
    private double baseAmount = 0.0;
    private double discount = 0.0;
    private double tax = 0.0;
    private double total = 0.0;

    private int reservationId = -1;
    private int guestId = -1;
    private String guestName = "Guest";
    private String roomType = "";

    public void setBillingDetails(String guestName, int reservationId, String roomType) {
        this.guestName = guestName;
        this.reservationId = reservationId;
        this.roomType = roomType;

        try (Connection conn = DatabaseUtil.getConnection()) {
            // Fetch guestId using reservation
            String guestQuery = "SELECT GuestID FROM Reservation WHERE ReservationID = ?";
            PreparedStatement guestStmt = conn.prepareStatement(guestQuery);
            guestStmt.setInt(1, reservationId);
            ResultSet guestRs = guestStmt.executeQuery();
            if (guestRs.next()) {
                guestId = guestRs.getInt("GuestID");
            }

            // Get price from room type
            String roomQuery = "SELECT Price FROM Room WHERE RoomType = ?";
            PreparedStatement stmt = conn.prepareStatement(roomQuery);
            stmt.setString(1, roomType);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                baseAmount = rs.getDouble("Price");
            } else {
                baseAmount = 400.00;
            }

            tax = baseAmount * 0.13;
            total = baseAmount + tax;

            billingDetailsArea.setText(String.format(
                    "Guest: %s\nRoom Type: %s\nAmount: $%.2f\nTax (13%%): $%.2f\nTotal: $%.2f",
                    guestName, roomType, baseAmount, tax, total
            ));

        } catch (SQLException e) {
            showAlert("Failed to retrieve billing details.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleApplyDiscount() {
        if (discountApplied) {
            showAlert("Discount has already been applied.");
            return;
        }

        discount = 40.00;
        if (discount >= baseAmount) {
            showAlert("Discount exceeds or equals base amount.");
            return;
        }

        total = (baseAmount - discount) * 1.13;
        billingDetailsArea.appendText(String.format("\nDiscount Applied: $%.2f\nNew Total: $%.2f", discount, total));
        discountApplied = true;
    }

    @FXML
    private void handleFinalizeCheckOut(ActionEvent event) {
        if (reservationId == -1 || guestId == -1) {
            showAlert("Missing guest or reservation ID.");
            return;
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            // 1. Update guest status
            String updateGuest = "UPDATE Guest SET Status = 'Checked Out' WHERE GuestID = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateGuest);
            updateStmt.setInt(1, guestId);
            updateStmt.executeUpdate();

            // 2. Insert billing
            String insertBilling = "INSERT INTO Billing (ReservationID, Amount, Tax, TotalAmount, Discount) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement billingStmt = conn.prepareStatement(insertBilling);
            billingStmt.setInt(1, reservationId);
            billingStmt.setDouble(2, baseAmount);
            billingStmt.setDouble(3, tax);
            billingStmt.setDouble(4, total);
            billingStmt.setDouble(5, discount);
            billingStmt.executeUpdate();

            // 3. Navigate to feedback screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/admin-feedback.fxml"));
            Parent root = loader.load();

            AdminFeedbackController feedbackController = loader.getController();
            feedbackController.setGuestAndReservationIds(guestId, reservationId);

            Stage stage = (Stage) billingDetailsArea.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (SQLException e) {
            showAlert("Database error during checkout.");
            e.printStackTrace();
        } catch (IOException e) {
            showAlert("Failed to load feedback screen.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        loadScene("/seneca/summer25/apdfinal/admin-guestdetails.fxml", event);
    }

    private void loadScene(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Could not navigate back.");
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
