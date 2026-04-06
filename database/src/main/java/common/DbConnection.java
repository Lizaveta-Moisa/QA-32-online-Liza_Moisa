package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static volatile DbConnection instance;

    private static final String URL = "jdbc:postgresql://localhost:5432/basketball";

    private static final String USER = "vibe";

    private static final String PASSWORD = "vibe";

    private DbConnection() {
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
