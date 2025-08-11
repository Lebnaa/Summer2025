package seneca.summer25.apdfinal.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Button newReservationButton;

    @FXML
    private Button searchGuestButton;

    @FXML
    private Button logoutButton;

    @FXML
    private void handleNewReservation(ActionEvent event) {
        System.out.println("New Reservation clicked");
        loadScene("kiosk-guestinfo.fxml", event);
    }

    @FXML
    private void handleSearchGuest(ActionEvent event) {
        System.out.println("Search Guest clicked");
        loadScene("admin-searchview.fxml", event);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        System.out.println("Logout clicked");
        loadScene("admin-loginview.fxml", event);
    }

    private void loadScene(String fxmlFileName, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/apdfinal/" + fxmlFileName));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Failed to load: " + fxmlFileName);
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Navigation Error");
        alert.setHeaderText("FXML Load Failure");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
