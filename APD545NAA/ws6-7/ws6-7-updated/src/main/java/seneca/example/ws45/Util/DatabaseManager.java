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

import seneca.example.ws45.Model.*;
import seneca.example.ws45.Model.*;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:inventory.db";

    public static void saveToDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();

            stmt.execute("CREATE TABLE IF NOT EXISTS parts (id INTEGER, name TEXT, price REAL, stock INTEGER, min INTEGER, max INTEGER, type TEXT, extra TEXT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS products (id INTEGER, name TEXT, price REAL, stock INTEGER, min INTEGER, max INTEGER)");

            stmt.execute("DELETE FROM parts");
            stmt.execute("DELETE FROM products");

            for (Part part : Inventory.getAllParts()) {
                String type = (part instanceof InHouse) ? "InHouse" : "Outsourced";
                String extra = (part instanceof InHouse) ? String.valueOf(((InHouse) part).getMachineId()) : ((Outsourced) part).getCompanyName();

                PreparedStatement ps = conn.prepareStatement("INSERT INTO parts VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, part.getId());
                ps.setString(2, part.getName());
                ps.setDouble(3, part.getPrice());
                ps.setInt(4, part.getStock());
                ps.setInt(5, part.getMin());
                ps.setInt(6, part.getMax());
                ps.setString(7, type);
                ps.setString(8, extra);
                ps.executeUpdate();
            }

            for (Product product : Inventory.getAllProducts()) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?, ?, ?)");
                ps.setInt(1, product.getId());
                ps.setString(2, product.getName());
                ps.setDouble(3, product.getPrice());
                ps.setInt(4, product.getStock());
                ps.setInt(5, product.getMin());
                ps.setInt(6, product.getMax());
                ps.executeUpdate();
            }
        }
    }

    public static void loadFromDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();

            ResultSet rsParts = stmt.executeQuery("SELECT * FROM parts");
            Inventory.getAllParts().clear();
            while (rsParts.next()) {
                String type = rsParts.getString("type");
                if ("InHouse".equals(type)) {
                    Inventory.addPart(new InHouse(
                        rsParts.getInt("id"),
                        rsParts.getString("name"),
                        rsParts.getDouble("price"),
                        rsParts.getInt("stock"),
                        rsParts.getInt("min"),
                        rsParts.getInt("max"),
                        Integer.parseInt(rsParts.getString("extra"))
                    ));
                } else {
                    Inventory.addPart(new Outsourced(
                        rsParts.getInt("id"),
                        rsParts.getString("name"),
                        rsParts.getDouble("price"),
                        rsParts.getInt("stock"),
                        rsParts.getInt("min"),
                        rsParts.getInt("max"),
                        rsParts.getString("extra")
                    ));
                }
            }

            ResultSet rsProducts = stmt.executeQuery("SELECT * FROM products");
            Inventory.getAllProducts().clear();
            while (rsProducts.next()) {
                Inventory.addProduct(new Product(
                    rsProducts.getInt("id"),
                    rsProducts.getString("name"),
                    rsProducts.getDouble("price"),
                    rsProducts.getInt("stock"),
                    rsProducts.getInt("min"),
                    rsProducts.getInt("max")
                ));
            }
        }
    }
}
