package seneca.summer25.apdfinal.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\lebna\\Summer2025\\APD545NAA\\FinalProject\\APDFINAL\\database\\databsefile.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
