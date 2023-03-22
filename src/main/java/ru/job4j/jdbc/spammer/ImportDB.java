package ru.job4j.jdbc.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private static String[] validation(String in) {
        if (!in.contains(";")) {
            throw new IllegalArgumentException(String.format("Line %s do not have correct delimiter ;", in));
        }
        String[] nameEmail = in.split(";");
        if (nameEmail.length != 2) {
            throw new IllegalArgumentException(String.format("Line %s do not have correct shape", in));
        }
        if (nameEmail[0].isEmpty() || nameEmail[1].isEmpty()) {
            throw new IllegalArgumentException(String.format("In line %s , name or email is blank", in));
        }
        return nameEmail;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            List<String> raw = rd.lines().toList();
            for (String str : raw) {
                String[] nameEmail = validation(str);
                users.add(new User(nameEmail[0], nameEmail[1]));
            }
            return users;
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users(name, email) values (?, ?)")) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Properties config = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var importDB = new ImportDB(config, "./data/spamList.txt");
        List<User> list = importDB.load();
        importDB.save(list);
    }
}
