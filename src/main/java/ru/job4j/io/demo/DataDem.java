package ru.job4j.io.demo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataDem {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("./data/data.dat");
        Files.createFile(path);
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path.toFile()));
             DataInputStream in = new DataInputStream(new FileInputStream(path.toFile()))) {
            out.writeInt(5);
            double c = in.readDouble();
            System.out.println(c);
        } catch (EOFException e) {
            System.out.println("End of file has been reached");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
