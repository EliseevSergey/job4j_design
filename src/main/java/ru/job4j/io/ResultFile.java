package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        String[] bufferString = new String[100];
        int i = 0;
        for (int x = 1; x < 11; x++) {
            for (int y = 1; y < 11; y++) {
                bufferString[i] = x + " * " + y + " = " + x * y;
                i++;
            }
        }
        try (FileOutputStream fos = new FileOutputStream("result11.txt")) {
            for (String item : bufferString) {
                fos.write(item.getBytes());
                fos.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
