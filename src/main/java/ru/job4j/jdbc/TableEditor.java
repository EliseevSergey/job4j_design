package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException {
        this.properties = properties;
        this.connection = initConnection();
    }

    private Connection initConnection() throws SQLException {
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
       return DriverManager.getConnection(url, login, password);
    }

    private boolean execStatement(String sql) throws SQLException {
        return connection.createStatement().execute(sql);
    }

    public void createTable(String tableName) throws SQLException {
            String sql = String.format(
                    "create table if not exists %s (%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name text");
            execStatement(sql);
    }

    public void dropTable(String tableName) throws SQLException {
            String sql = String.format(
                    "drop table %s",
                    tableName);
            execStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
            String sql = String.format(
                    "alter table %s add column %s %s",
                    tableName, columnName, type);
            execStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
            String sql = String.format(
                    "alter table %s drop column %s",
                    tableName, columnName);
            execStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
            String sql = String.format(
                    "alter table %s rename column %s to %s",
                    tableName, columnName, newColumnName);
            execStatement(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TableEditor te = new TableEditor(config);
        te.createTable("from_jdbc");
        System.out.println(te.getTableScheme("from_jdbc"));
        te.dropTable("from_jdbc");
        te.createTable("from_jdbc");
        System.out.println(te.getTableScheme("from_jdbc"));
        te.addColumn("from_jdbc", "added", "TEXT");
        System.out.println(te.getTableScheme("from_jdbc"));
        te.dropColumn("from_jdbc", "added");
        System.out.println(te.getTableScheme("from_jdbc"));
        te.renameColumn("from_jdbc", "name", "nickname");
        System.out.println(te.getTableScheme("from_jdbc"));
    }
}
