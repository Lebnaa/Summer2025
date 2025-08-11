package seneca.summer25.apdfinal.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import seneca.summer25.apdfinal.Utils.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminFeedbackController {

    @FXML
    private Slider ratingSlider;

    @FXML
    private TextArea commentsArea;

    private int guestId = -1;
    private int reservationId = -1;


    public void setGuestAndReservationIds(int guestId, int reservationId) {
        this.guestId = guestId;
        this.reservationId = reservationId;
    }

    @FXML
    private void handleSubmit() {
        int rating = (int) ratingSlider.getValue();
        String comments = commentsArea.getText().trim();

        if (guestId == -1 || reservationId == -1) {
            showAlert("Invalid Data", "Guest or reservation information is missing.");
            return;
        }

        if (rating <= 2 && comments.isEmpty()) {
            showAlert("Feedback Required", "Please provide comments for a low rating.");
            return;
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Feedback (GuestID, ReservationID, Comments, Rating) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, guestId);
            stmt.setInt(2, reservationId);
            stmt.setString(3, comments);
            stmt.setInt(4, rating);
            stmt.executeUpdate();

            showAlert("Feedback Submitted", "Thank you for your feedback!");
            goToDashboard(); // Navigate to dashboard after submission

        } catch (SQLException e) {
            showAlert("Database Error", "Could not save feedback.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSkip() {
        showAlert("Feedback Skipped", "Thank you!");
        goToDashboard(); // Still go to dashboard
    }

    private void goToDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/seneca/summer25/apdfinal/admin-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) commentsArea.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Navigation Error", "Could not return to dashboard.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
