package ru.job4j.io.finder.depretiated;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CfgLoad {
    private final String pathToProp;
    private final Map<String, String> keyVal = new HashMap<>();

    public CfgLoad(String pathToProp) {
        this.pathToProp = pathToProp;
    }

    private List<String> load() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(this.pathToProp))) {
            List<String> list = in.lines()
                    .filter(s -> !s.startsWith("#"))
                    .filter(s -> !s.isBlank())
                    .filter(s -> s.startsWith("-"))
                    .map(s -> s.substring(1))
                    .toList();
            rsl.addAll(list);
            } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void validation() {
        for (String str : load()) {
            if (!str.contains("=")) {
                throw new IllegalArgumentException(String.format("Argument pair [%s] does not contain =.", str));
            }
            String[] array = str.split("=", 2);
            if (array.length != 2) {
                throw new IllegalArgumentException(String.format("Argument pair [%s] does not contain key or value", str));
            }
            if (array[0].isBlank() || array[1].isBlank()) {
                throw new IllegalArgumentException(String.format("Argument pair [%s] has no key or value", str));
            }
            keyVal.put(array[0], array[1]);
        }
    }

    @Override
    public String toString() {
        return "CfgLoad{" + "pathToProp='"
                + pathToProp + '\'' + ", keyVal=" + keyVal + '}';
    }
}
