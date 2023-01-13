package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File(args[0]);
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is not defined");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("File name: %s. Size: %s", file.getName(),  file.length()));
    }
}
