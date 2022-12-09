package ru.job4j.io;

import java.nio.file.Path;

public class Trial {
    public static void main(String[] args) {
        String str = "C:\\projects\\job4j_design\\data";
        Path path = Path.of(str);
        System.out.println(path);
        System.out.println(path.toFile().getPath());
        System.out.println(path.toFile().getName());
        System.out.println(path.toFile().getName());
    }
}
