import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class Step1Standard {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = Database.connect()) {
            DatabaseMetaData meta = connection.getMetaData();
            IO.println("Interface utilisée  : java.sql.Connection");
            IO.println("Classe réelle       : " + connection.getClass().getName());
            IO.println("Driver              : " + meta.getDriverName() + " " + meta.getDriverVersion());
            IO.println("Version JDBC        : " + meta.getJDBCMajorVersion() + "." + meta.getJDBCMinorVersion());
            IO.println("Base de données     : " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion());
        }
    }
}
