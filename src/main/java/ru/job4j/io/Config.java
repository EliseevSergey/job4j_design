package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
                    throw new IllegalArgumentException(" Line: "  + str + " does not contain =.");
                }
                String[] array = str.split("=", 2);
                if (array.length != 2) {
                    throw new IllegalArgumentException(" Line: " + str + " does not contain key or value");
                }
                if (array[0].isBlank() || array[1].isBlank()) {
                    throw new IllegalArgumentException(" Line: "  + str + " has no key or value");
                }
                values.put(array[0], array[1]);
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

