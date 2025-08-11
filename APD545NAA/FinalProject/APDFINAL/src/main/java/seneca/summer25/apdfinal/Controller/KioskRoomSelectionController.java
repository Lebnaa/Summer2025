package seneca.summer25.apdfinal.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seneca.summer25.apdfinal.Model.Room;
import seneca.summer25.apdfinal.Model.RoomType;

import java.io.IOException;
import java.util.List;

public class KioskRoomSelectionController {

    @FXML
    private ListView<String> roomListView;

    @FXML
    private Button nextButton;

    private Room selectedRoom = new Room();

    private int numAdults;
    private int numChildren;
    private String guestName;
    private String guestEmail;
    private String guestAddress;
    private String guestPhone;
    private String checkInDate;
    private String checkOutDate;

    @FXML
    public void initialize() {
        List<String> roomOptions = FXCollections.observableArrayList(
                RoomType.SINGLE.name(),
                RoomType.DOUBLE.name(),
                RoomType.DELUXE.name(),
                RoomType.PENT_HOUSE.name()
        );
        roomListView.setItems(FXCollections.observableArrayList(roomOptions));
    }

    public void setGuestDetails(int adults, int children, String checkIn, String checkOut,
                                String name, String email, String address, String phone) {
        this.numAdults = adults;
        this.numChildren = children;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.guestName = name;
        this.guestEmail = email;
        this.guestAddress = address;
        this.guestPhone = phone;

        System.out.println("RoomSelectionController received guest:");
        System.out.println("Name: " + name + ", Email: " + email + ", Address: " + address + ", Phone: " + phone);
        System.out.println("Check-In: " + checkIn + ", Check-Out: " + checkOut);
    }

    @FXML
    private void handleNext() {
        String selectedType = roomListView.getSelectionModel().getSelectedItem();
        if (selectedType == null) {
            showAlert("Please select a room type before proceeding.");
            return;
        }

        selectedRoom.setRoomType(RoomType.valueOf(selectedType));
        int totalGuests = numAdults + numChildren;
        int roomCapacity = getRoomCapacity(selectedRoom.getRoomType());
        int roomsNeeded = (int) Math.ceil((double) totalGuests / roomCapacity);

        String message = String.format("You have %d guests.\nBased on %s room (capacity %d), you'll need %d room(s).",
                totalGuests, selectedRoom.getRoomType(), roomCapacity, roomsNeeded);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Room Recommendation");
        alert.setHeaderText("Room Calculation Based on Guest Count");
        alert.setContentText(message + "\n\nDo you want to proceed?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("Choose Another");
        alert.getButtonTypes().setAll(yes, no);

        alert.showAndWait().ifPresent(response -> {
            if (response == yes) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/kiosk-checkin.fxml"));
                    Parent root = loader.load();

                    KioskCheckInController controller = loader.getController();
                    controller.setGuestDetails(
                            numAdults, numChildren,
                            checkInDate, checkOutDate,
                            selectedRoom.getRoomType().name(),
                            guestName, guestEmail, guestAddress, guestPhone
                    );

                    Stage stage = (Stage) nextButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (IOException e) {
                    showError("Failed to load the check-in screen.");
                    e.printStackTrace();
                }
            }
        });
    }

    private int getRoomCapacity(RoomType type) {
        return switch (type) {
            case SINGLE -> 1;
            case DOUBLE -> 2;
            case DELUXE -> 3;
            case PENT_HOUSE -> 4;
        };
    }

    @FXML
    private void handleViewRules() {
        showAlert("Hotel Rules:\n- Check-in after 2 PM\n- Max 2 guests per room\n- No smoking indoors");
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/kiosk-guestdetails.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Failed to load guest details screen.");
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Navigation Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
