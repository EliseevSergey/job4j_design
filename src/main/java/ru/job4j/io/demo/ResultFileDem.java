package ru.job4j.io.demo;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResultFileDem {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("./data/dataresult.txt")) {
            out.write("Hello, world!".getBytes());
            out.write(System.lineSeparator().getBytes());
            String fileData = "string";
            Files.write(Paths.get("name.txt"), fileData.getBytes());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}