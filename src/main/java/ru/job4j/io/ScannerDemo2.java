package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ScannerDemo2 {
    public static void main(String[] args) throws IOException {
        String instr = "Name: Tom Age: 28 ID: 77";
        Scanner conin = new Scanner(instr);
        conin.useDelimiter("7");
        conin.findInLine("Age: ");
        if (conin.hasNext()) {
            System.out.println(conin.next());
        } else {
            System.out.println("fail");
            conin.close();
        }
        byte[] barray = "AB".getBytes(Charset.forName("Windows-1252"));
        for (byte b : barray) {
            System.out.println(b);
        }
        FileInputStream fis = new FileInputStream("./data/textAB.rtf");
        StringBuilder sb = new StringBuilder();
        while (fis.read()!=-1)
        sb.append(fis.read());
        System.out.println(sb);
    }
}
