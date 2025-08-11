package seneca.summer25.apdfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seneca.summer25.apdfinal.Utils.DatabaseUtil;

import java.io.IOException;
import java.sql.*;

public class KioskCheckInController {

    @FXML
    private Label guestNameLabel;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label checkInDateLabel;

    @FXML
    private Label checkOutDateLabel;

    @FXML
    private Label guestCountLabel;

    @FXML
    private TextField phoneVerificationField;

    private String guestName;
    private String guestEmail;
    private String guestAddress;
    private String guestPhone;
    private String originalPhoneNumber;
    private String checkInDate;
    private String checkOutDate;
    private String roomType;
    private int totalGuests;

    public void setGuestDetails(int adults, int children, String checkIn, String checkOut,
                                String roomType, String name, String email, String address, String phone) {
        this.totalGuests = adults + children;
        this.roomType = roomType;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.guestName = name;
        this.guestEmail = email;
        this.guestAddress = address;
        this.originalPhoneNumber = phone;

        guestNameLabel.setText(name);
        roomTypeLabel.setText(roomType);
        checkInDateLabel.setText(checkIn);
        checkOutDateLabel.setText(checkOut);
        guestCountLabel.setText(String.valueOf(totalGuests));
    }

    @FXML
    private void handleBack(ActionEvent event) {
        loadScene("/seneca/summer25/apdfinal/kiosk-roomselection.fxml", event);
    }

    @FXML
    private void handleConfirmCheckIn(ActionEvent event) {
        guestPhone = phoneVerificationField.getText().trim();

        if (guestPhone.isEmpty()) {
            showAlert("Please enter your phone number for verification.");
            return;
        }

        if (!guestPhone.equals(originalPhoneNumber)) {
            showAlert("Phone number does not match the one provided earlier.");
            return;
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            conn.setAutoCommit(false);

            // 1. Insert into Guest
            String guestSql = "INSERT INTO Guest (name, phoneNumber, email, address, checkInDate, checkOutDate, roomType, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement guestStmt = conn.prepareStatement(guestSql, Statement.RETURN_GENERATED_KEYS);
            guestStmt.setString(1, guestName);
            guestStmt.setString(2, guestPhone);
            guestStmt.setString(3, guestEmail);
            guestStmt.setString(4, guestAddress);
            guestStmt.setString(5, checkInDate);
            guestStmt.setString(6, checkOutDate);
            guestStmt.setString(7, roomType);
            guestStmt.setString(8, "Confirmed");
            guestStmt.executeUpdate();

            int guestId = -1;
            ResultSet guestKeys = guestStmt.getGeneratedKeys();
            if (guestKeys.next()) guestId = guestKeys.getInt(1);

            // 2. Get Room ID
            int roomId = -1;
            String roomQuery = "SELECT RoomID FROM Room WHERE RoomType = ?";
            PreparedStatement roomStmt = conn.prepareStatement(roomQuery);
            roomStmt.setString(1, roomType);
            ResultSet roomResult = roomStmt.executeQuery();
            if (roomResult.next()) roomId = roomResult.getInt("RoomID");

            // 3. Insert into Reservation
            String resSql = "INSERT INTO Reservation (GuestID, RoomID, CheckInDate, CheckOutDate, NumberOfGuests, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement resStmt = conn.prepareStatement(resSql);
            resStmt.setInt(1, guestId);
            resStmt.setInt(2, roomId);
            resStmt.setString(3, checkInDate);
            resStmt.setString(4, checkOutDate);
            resStmt.setInt(5, totalGuests);
            resStmt.setString(6, "Confirmed");
            resStmt.executeUpdate();

            conn.commit();

            showAlert("Check-in confirmed! Proceeding to Booking Summary...");

            // Load summary screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/kiosk-bookingconfirmation.fxml"));
            Parent root = loader.load();
            KioskBookingConfirmationController controller = loader.getController();
            controller.setBookingSummary("Name: " + guestName +
                    "\nRoom: " + roomType +
                    "\nCheck-In: " + checkInDate +
                    "\nCheck-Out: " + checkOutDate +
                    "\nGuests: " + totalGuests +
                    "\nPhone: " + guestPhone);

            Stage stage = (Stage) phoneVerificationField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (SQLException | IOException e) {
            showError("Failed to store booking data or load screen.");
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Check-in Failed");
        alert.setContentText(message);
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
            showError("Could not load: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
