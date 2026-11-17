import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Step2Connection {
    public static void main(String[] args) {
        tryToConnect("bon mot de passe", "jdbc:mysql://db:3306/todo?sslMode=DISABLED&allowPublicKeyRetrieval=true", "student");
        tryToConnect("mauvais mot de passe", "jdbc:mysql://db:3306/todo?sslMode=DISABLED&allowPublicKeyRetrieval=true", "oups");
        tryToConnect("mauvaise base", "jdbc:mysql://db:3306/todos?sslMode=DISABLED&allowPublicKeyRetrieval=true", "student");
    }

    private static void tryToConnect(String label, String url, String password) {
        try (Connection connection = DriverManager.getConnection(url, "student", password)) {
            IO.println(label + " : connecté, valide = " + connection.isValid(2));
        } catch (SQLException exception) {
            IO.println(label + " : échec (SQLState " + exception.getSQLState() + ") " + exception.getMessage());
        }
    }
}
