package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;

public class ConsoleRead {
    public static void main(String[] args) throws IOException {
        Console console = System.console();
        console.readLine("New line to console");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
        char c;
        String str;
        do {
             c = (char) br.read();
             System.out.println(c);
        } while (c !='q');
        System.out.println("Char end");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("stop"));
        System.out.println("str end");

        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println("Test string: ");
        int i = -7;
        double d = 4.5 - 7;
        String string ="String";
        pw.println(d);
        pw.println(string);




    }
}
