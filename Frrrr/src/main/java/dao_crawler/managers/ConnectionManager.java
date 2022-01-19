package dao_crawler.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection con;

    //TODO: помочь синглтону с многопоточностью
    //TODO: заменить синглтон на что-то более могущественное
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5342/crawler", "mordariy", "admin");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

}
