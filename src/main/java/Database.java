import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://db:3306/todo?sslMode=DISABLED&allowPublicKeyRetrieval=true";
    private static final String USER = "student";
    private static final String PASSWORD = "student";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
