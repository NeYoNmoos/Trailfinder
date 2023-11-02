package data;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://ep-misty-cake-26378207.eu-central-1.aws.neon.tech/Trailfinder?user=MarkPleschberger&password=dA1jpSrWCq4O";

    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL);
            c.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
