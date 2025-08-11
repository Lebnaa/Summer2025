/**********************************************
 Workshop #6&7
 Course:APD - Semester
 Last Name:Noori
 First Name:Lebna
 ID:157672205
 Section:NAA
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date:July 31/2025
 **********************************************/
package seneca.example.ws45.Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seneca.example.ws45.Model.Inventory;
import seneca.example.ws45.Model.Part;
import seneca.example.ws45.Model.Product;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static void saveDataToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Save serializable ArrayLists
            out.writeObject(new ArrayList<>(Inventory.getAllParts()));
            out.writeObject(new ArrayList<>(Inventory.getAllProducts()));
            System.out.println("Data saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    public static void loadDataFromFile(String filename) {
        File file = new File(filename);
        System.out.println("Trying to load file: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("Data file does not exist: " + filename);
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            ArrayList<Part> partsList = (ArrayList<Part>) in.readObject();
            ArrayList<Product> productsList = (ArrayList<Product>) in.readObject();

            Inventory.setAllParts(FXCollections.observableArrayList(partsList));
            Inventory.setAllProducts(FXCollections.observableArrayList(productsList));

            System.out.println("Data loaded from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load data: " + e.getMessage());
        }
    }
}
