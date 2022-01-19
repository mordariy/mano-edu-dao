package dao_many_to_many.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5342/postgres", "postgres", "admin");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

}
