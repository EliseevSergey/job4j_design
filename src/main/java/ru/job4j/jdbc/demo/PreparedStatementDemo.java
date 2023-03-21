package ru.job4j.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStatementDemo {
    private Connection connection;

    public PreparedStatementDemo() throws Exception {
        initConnection();
    }
    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/city";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public void insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("insert into cities(name, population) values (?, ?)")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        PreparedStatementDemo prepStatement = new PreparedStatementDemo();
        prepStatement.insert(new City(1, "Chita", 400000));
    }
}
