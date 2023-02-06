package ru.job4j.io.finder;

import java.nio.file.Path;

public class Adress {
    public static void main(String[] args) {
        String adres1 = "FindMeCopy.txt";
        String path2 = "\\C\\projects\\job4j_design\\FindMeCopy.txt";
        Path path = Path.of(path2);
        System.out.println(path.getFileName().toString());
    }
}
