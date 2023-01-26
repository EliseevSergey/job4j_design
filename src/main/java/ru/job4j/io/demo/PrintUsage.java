package ru.job4j.io.demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream ps = new PrintStream(new FileOutputStream("./data/printUsage.txt"));
             PrintStream ps2 = new PrintStream("./data/printUsageDirect");
             PrintWriter pw = new PrintWriter("./data/printUsage.txt")
        ) {
          ps.println("new string from PrintStream. Из PrintStream в FileOutputStream");
          ps.write("new line /n".getBytes());
          ps2.println("new string from PrintStream directly. Without FileOutPutSteam Из PrintStream в FileOutputStream");
          pw.println("String from PW");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
