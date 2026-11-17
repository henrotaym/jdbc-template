import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Step4Prepared {
    public static void main(String[] args) throws SQLException {
        String typedByUser = "x' OR '1'='1";

        try (Connection connection = Database.connect()) {
            IO.println("Concaténation :");
            try (Statement statement = connection.createStatement();
                    ResultSet rows = statement.executeQuery("SELECT name FROM task WHERE name = '" + typedByUser + "'")) {
                while (rows.next()) {
                    IO.println("  " + rows.getString("name"));
                }
            }

            IO.println("PreparedStatement :");
            try (PreparedStatement query = connection.prepareStatement("SELECT id FROM task WHERE name = ?")) {
                query.setString(1, typedByUser);
                try (ResultSet rows = query.executeQuery()) {
                    IO.println("  " + (rows.next() ? "tâche n°" + rows.getInt("id") : "aucune tâche"));
                }

                query.setString(1, "Rendre le CRUD");
                try (ResultSet rows = query.executeQuery()) {
                    IO.println("  " + (rows.next() ? "tâche n°" + rows.getInt("id") : "aucune tâche"));
                }
            }
        }
    }
}
