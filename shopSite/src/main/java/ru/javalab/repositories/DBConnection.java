package ru.javalab.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private Connection connection;
    String urlProperties;

    public DBConnection(String urlProperties) {
        this.urlProperties = urlProperties;
    }

    public Connection getConnection() {
        Properties property = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(urlProperties);
            property.load(fis);
            String dbUrl = property.getProperty("db.url");
            String dbUser = property.getProperty("db.user");
            String dbPassword = property.getProperty("db.password");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
