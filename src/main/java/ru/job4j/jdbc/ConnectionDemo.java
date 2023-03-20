package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config cfg = new Config("./data/app.properties");
        cfg.load();
        String driverClass = cfg.value("driver_class");
        Class.forName(driverClass);
        String url = cfg.value("url");
        String username = cfg.value("username");
        String password = cfg.value("password");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}