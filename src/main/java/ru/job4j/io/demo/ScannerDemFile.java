package ru.job4j.io.demo;

import java.io.*;
import java.util.Scanner;

public class ScannerDemFile {
    public static void main(String[] args) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("./data/demo.txt"));
            Scanner sc = new Scanner(System.in)) {
            String end = "end";
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (!end.equals(line)) {
                    sb.append(line).append(System.lineSeparator());
                } else {
                    break;
                }
            }
            System.out.println(sb);
            pw.write(sb.toString());
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
}

