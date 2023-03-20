package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            List<String> list = in.lines()
                    .filter(i -> !i.startsWith("#"))
                    .filter(i -> !i.isBlank())
                    .toList();
            for (String str : list) {
                if (!str.contains("=")) {
                    throw new IllegalArgumentException(String.format("Line[%s] does not contain =.", str));
                }
                String[] array = str.split("=", 2);
                if (array.length != 2) {
                    throw new IllegalArgumentException(String.format("Line[%s] does not contain key or value", str));
                }
                if (array[0].isBlank() || array[1].isBlank()) {
                    throw new IllegalArgumentException(String.format("Line[%s] has no key or value", str));
                }
                Pattern pattern = Pattern.compile("\\w+$");
                Matcher matcher = pattern.matcher(array[0]);
                if (!matcher.find()) {
                    throw new IllegalArgumentException(String.format("Key[%s] characters are out of [a-zA-Z0-9_]", array[0]));
                } else {
                    String key = matcher.group(0);
                    values.put(key, array[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/app.properties"));
    }
}

