package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Buffered {
    public static void main(String[] args) {
        Path input = Path.of("./data/inForBuf.txt");
        try {
            Files.createFile(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(input.toFile()));
                FileWriter fw = new FileWriter(input.toFile());
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./data/outForBuf.txt", true))
        ) {
            fw.write("THIS IS JUST FOR INPUT");
            fw.flush();
            int i;
            while ((i = bis.read()) != -1) {
                System.out.print((char) i);
            }
            bos.write(bis.readAllBytes());
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
