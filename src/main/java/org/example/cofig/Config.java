package org.example.cofig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "postgres";
    public static Connection connectionToDatabase(){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
