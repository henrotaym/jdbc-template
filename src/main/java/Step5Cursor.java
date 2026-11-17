import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Step5Cursor {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = Database.connect()) {
            IO.println("Avancer avec next() :");
            try (Statement statement = connection.createStatement();
                    ResultSet rows = statement.executeQuery("SELECT id, name, done FROM task ORDER BY id")) {
                while (rows.next()) {
                    String box = rows.getBoolean("done") ? "[x]" : "[ ]";
                    IO.println("  ligne " + rows.getRow() + " : " + box + " " + rows.getInt("id") + ". " + rows.getString("name"));
                }
            }

            IO.println("Une valeur NULL :");
            try (Statement statement = connection.createStatement();
                    ResultSet rows = statement.executeQuery("SELECT MAX(id) AS last_done FROM task WHERE done AND id > 100")) {
                rows.next();
                int lastDone = rows.getInt("last_done");
                IO.println("  getInt = " + lastDone + ", wasNull = " + rows.wasNull());
            }

            IO.println("Curseur déplaçable :");
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rows = statement.executeQuery("SELECT name FROM task ORDER BY id")) {
                rows.last();
                IO.println("  last()      : " + rows.getString("name") + " (ligne " + rows.getRow() + ")");
                rows.previous();
                IO.println("  previous()  : " + rows.getString("name"));
                rows.absolute(2);
                IO.println("  absolute(2) : " + rows.getString("name"));
            }

            IO.println("Ligne par ligne depuis le serveur (streaming) :");
            try (Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                statement.setFetchSize(Integer.MIN_VALUE);
                try (ResultSet rows = statement.executeQuery("SELECT name FROM task ORDER BY id")) {
                    while (rows.next()) {
                        IO.println("  " + rows.getString("name"));
                    }
                }
            }
        }
    }
}
