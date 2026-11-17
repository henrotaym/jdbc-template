import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Step3Statement {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = Database.connect();
                Statement statement = connection.createStatement()) {

            try (ResultSet rows = statement.executeQuery("SELECT COUNT(*) AS total FROM task")) {
                rows.next();
                IO.println("executeQuery  -> " + rows.getInt("total") + " tâches");
            }

            int updated = statement.executeUpdate("UPDATE task SET done = TRUE WHERE name LIKE 'Lire%'");
            IO.println("executeUpdate -> " + updated + " ligne modifiée");

            statement.executeUpdate("INSERT INTO task (name) VALUES ('Tâche temporaire')", Statement.RETURN_GENERATED_KEYS);
            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                IO.println("clé générée   -> id " + keys.getInt(1));
            }

            boolean hasRows = statement.execute("DELETE FROM task WHERE name = 'Tâche temporaire'");
            IO.println("execute       -> ResultSet ? " + hasRows + ", lignes touchées : " + statement.getUpdateCount());
        }
    }
}
