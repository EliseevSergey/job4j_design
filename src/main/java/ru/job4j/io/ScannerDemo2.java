package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ScannerDemo2 {
    public static void main(String[] args) throws IOException {
      /*  String instr = "Name: Tom Age: 28 ID: 77";
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
        try (FileReader fileReader = new FileReader("./data/textAB.txt")) {
            StringBuilder sb = new StringBuilder();
            int simbolNum = fileReader.read();
            while (simbolNum != -1) {
                sb.append(simbolNum);
                sb.append(System.lineSeparator());
                simbolNum = fileReader.read();
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try (FileInputStream fis = new FileInputStream("./data/textAB.txt")) {
            int bb = fis.read();
            StringBuilder sb = new StringBuilder();
            while (bb != -1) {
                sb.append(bb).append(System.lineSeparator());
                bb = fis.read();
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileInputStream("./data/textAB.txt"))) {
            StringBuilder sbBuf = new StringBuilder();
            int end = br.readLine();
            while
            br.lines();
        }
    }
}
