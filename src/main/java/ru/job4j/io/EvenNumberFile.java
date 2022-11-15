package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder txt = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                txt.append((char) read);
            }
            String[] lines = txt.toString().split(System.lineSeparator());
            for (String str : lines) {
                System.out.println("num: " + str + "  " +  (Integer.parseInt(str) % 2 == 0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
