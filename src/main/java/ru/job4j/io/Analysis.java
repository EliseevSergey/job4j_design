package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            BufferedReader in = new BufferedReader(new FileReader(source));
            boolean printSwitcher = false;
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] array = line.split(" ");
                if (((array[0].equals("400") || (array[0].equals("500"))) && (!printSwitcher))) {
                    out.print(array[1]);
                    printSwitcher = true;
                }
                if (!array[0].equals("400") && !array[0].equals("500") && printSwitcher) {
                    out.println(String.format(";%s", array[1]));
                    printSwitcher = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis fail = new Analysis();
        fail.unavailable("./data/server.txt", "./data/unavailableServer.csv");
    }
}

