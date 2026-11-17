import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Step6Transaction {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = Database.connect()) {
            IO.println("Avant : " + count(connection) + " tâches");

            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("UPDATE task SET done = TRUE");
                statement.executeUpdate("DELETE FROM task WHERE done");
                IO.println("Pendant la transaction : " + count(connection) + " tâches");
                throw new SQLException("Panne simulée avant la validation.");
            } catch (SQLException exception) {
                connection.rollback();
                IO.println("rollback : " + exception.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }

            IO.println("Après : " + count(connection) + " tâches");
        }
    }

    private static int count(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
                ResultSet rows = statement.executeQuery("SELECT COUNT(*) FROM task")) {
            rows.next();
            return rows.getInt(1);
        }
    }
}
