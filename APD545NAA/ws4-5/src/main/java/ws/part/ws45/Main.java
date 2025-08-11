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


package ws.part.ws45;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ws/part/ws45/login.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
