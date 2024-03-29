package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileWithBuf {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}