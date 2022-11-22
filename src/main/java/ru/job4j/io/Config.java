package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            list = in.lines()
                    .filter(i -> !i.contains("#"))
                    .collect(Collectors.toList());
            for (String str : list) {
                if (!str.contains("=")) {
                    throw new IllegalArgumentException();
                }
                String[] array = str.split("=", 3);
                if (array.length != 3) {
                    throw new IllegalArgumentException();
                }
                if (array[0].isBlank() || array[2].isBlank()) {
                    throw new IllegalArgumentException();
                }
                values.put(array[0], array[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
        //throw new UnsupportedOperationException("Don't impl this method yet!");
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
        System.out.println(new Config("app.properties"));
    }
}

