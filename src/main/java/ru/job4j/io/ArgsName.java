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

    private void argValidation(String arg, int equalIndex) {
            if (equalIndex == -1) {
                throw new IllegalArgumentException(String.format("Argument [%s] does not contain [=]", arg));
            }
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Argument [%s] does not begin with [-]", arg));
            }
            String key = arg.substring(1, equalIndex);
            String val = arg.substring(equalIndex + 1);
            if (key.isEmpty()) {
                throw new IllegalArgumentException(String.format("Key is empty for argument [%s]", arg));
            }
            if (val.isEmpty()) {
                throw new IllegalArgumentException(String.format("Value is empty for argument [%s]", arg));
            }
        }

    private void parse(String[] args) {
        for (String arg : args) {
            int deviderIndex = arg.indexOf("=");
            argValidation(arg, deviderIndex);
            int indexEqual = arg.indexOf("=");
            String key = arg.substring(1, indexEqual);
            String val = arg.substring(indexEqual + 1);
            values.put(key, val);
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