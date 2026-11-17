import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://db:3306/todo?sslMode=DISABLED&allowPublicKeyRetrieval=true";
    private static final String USER = "student";
    private static final String PASSWORD = "student";

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            IO.println("Connecté à " + connection.getMetaData().getDatabaseProductName() + " "
                    + connection.getMetaData().getDatabaseProductVersion());
        }
    }
}
