package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Key [%s] is not exist", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String str : args) {
            if (!str.contains("=")) {
                throw new IllegalArgumentException(String.format("Argument [%s] does not contain [=]", str));
            }
            if (!str.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Argument [%s] does not begin with [-]", str));
            }
            String[] keyVal = str.split("=", 2);
            if (keyVal[0].equals("-")) {
                throw new IllegalArgumentException(String.format("Key is empty for argument [%s]", str));
            }
            if (keyVal[1].isEmpty()) {
                throw new IllegalArgumentException(String.format("Value is empty for argument [%s]", str));
            }
            values.put(keyVal[0].split("-", 2)[1], keyVal[1]);
            }
        }


    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Nothing to load");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
        System.out.println(zip.get("encoding"));
    }
}