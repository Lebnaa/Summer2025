package seneca.summer25.apdfinal.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seneca.summer25.apdfinal.Model.Guest;
import seneca.summer25.apdfinal.Utils.DatabaseUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminSearchGuestController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Guest> guestTableView;

    @FXML
    private TableColumn<Guest, String> colGuestName;

    @FXML
    private TableColumn<Guest, String> colPhoneNumber;

    @FXML
    private TableColumn<Guest, String> colReservationStatus;

    @FXML
    private Button viewDetailsButton;

    @FXML
    private Button cancelReservationButton;

    private final ObservableList<Guest> guestList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colGuestName.setCellValueFactory(data -> data.getValue().nameProperty());
        colPhoneNumber.setCellValueFactory(data -> data.getValue().phoneNumberProperty());
        colReservationStatus.setCellValueFactory(data -> data.getValue().statusProperty());

        loadAllGuests();
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim().toLowerCase();
        guestList.clear();

        if (query.isEmpty()) {
            showAlert("Please enter a guest name or phone number.");
            return;
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Guest WHERE LOWER(Name) LIKE ? OR PhoneNumber LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Guest guest = new Guest();
                guest.setGuestID(rs.getInt("GuestID"));
                guest.setName(rs.getString("Name"));
                guest.setPhoneNumber(rs.getString("PhoneNumber"));
                guest.setEmail(rs.getString("Email"));
                guest.setAddress(rs.getString("Address"));
                guest.setCheckInDate(rs.getString("CheckInDate"));
                guest.setCheckOutDate(rs.getString("CheckOutDate"));
                guest.setRoomType(rs.getString("RoomType"));
                guest.setStatus(rs.getString("Status"));

                guestList.add(guest);
            }

            if (guestList.isEmpty()) {
                showAlert("No matching guests found.");
            }

        } catch (SQLException e) {
            showAlert("Database error while searching.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewDetails() {
        Guest selected = guestTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a guest to view details.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/admin-guestdetails.fxml"));
            Parent root = loader.load();

            // Get the controller and pass guest details
            AdminGuestDetailsController controller = loader.getController();

            String guestDetails = "Name: " + selected.getName() +
                    "\nPhone: " + selected.getPhoneNumber() +
                    "\nRoom: " + selected.getRoomType() +
                    "\nCheck-In: " + selected.getCheckInDate() +
                    "\nCheck-Out: " + selected.getCheckOutDate() +
                    "\nStatus: " + selected.getStatus();

            controller.setGuestDetails(guestDetails, selected.getGuestID(), selected.getRoomType());  // 👈 include guest ID and room type

            Stage stage = (Stage) guestTableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            showAlert("Failed to load guest details screen.");
            e.printStackTrace();
        }
    }


    @FXML
    private void handleCancelReservation() {
        Guest selected = guestTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a reservation to cancel.");
            return;
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "UPDATE Guest SET Status = 'Cancelled' WHERE GuestID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, selected.getGuestID());
            pstmt.executeUpdate();

            selected.setStatus("Cancelled");
            guestTableView.refresh();

            showAlert("Reservation cancelled for: " + selected.getName());
        } catch (SQLException e) {
            showAlert("Error cancelling reservation.");
            e.printStackTrace();
        }
    }

    private void loadAllGuests() {
        guestList.clear();

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Guest";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Guest guest = new Guest();
                guest.setGuestID(rs.getInt("GuestID"));
                guest.setName(rs.getString("Name"));
                guest.setPhoneNumber(rs.getString("PhoneNumber"));
                guest.setEmail(rs.getString("Email"));
                guest.setAddress(rs.getString("Address"));
                guest.setCheckInDate(rs.getString("CheckInDate"));
                guest.setCheckOutDate(rs.getString("CheckOutDate"));
                guest.setRoomType(rs.getString("RoomType"));
                guest.setStatus(rs.getString("Status"));

                guestList.add(guest);
            }

            guestTableView.setItems(guestList);
            System.out.println("All guests loaded: " + guestList.size());

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Failed to load guests from the database.");
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/admin-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) guestTableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Could not load the admin dashboard.");
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
