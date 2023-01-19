package ru.job4j.io;

import java.io.*;
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

        try (FileReader fileReader = new FileReader("./data/textAB.txt")) {
            StringBuilder sb = new StringBuilder();
            int simbolNum = fileReader.read();
            while (simbolNum != -1) {
                sb.append((char)simbolNum);
                simbolNum = fileReader.read();
            }
            System.out.println("FileReader with (char) " + System.lineSeparator() + sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("./data/textAB.txt"))) {
            StringBuilder sb = new StringBuilder();
            for (String str = br.readLine(); str != null; str = br.readLine()) {
                sb.append(str);
                sb.append(System.lineSeparator());
            }
            System.out.println("Buffered Reader: " + sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream("./data/textAB.txt")) {
            int bb = fis.read();
            StringBuilder sb = new StringBuilder();
            while (bb != -1) {
                sb.append(bb).append(System.lineSeparator());
                bb = fis.read();
            }
            System.out.println("File input Stream" + System.lineSeparator() + sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./data/textAB.txt"))) {
            StringBuilder sb = new StringBuilder();
            int end = bis.read();
            while (end != -1) {
                sb.append(end).append(System.lineSeparator());
                end = bis.read();
            }

            System.out.println("Buffered input: " + System.lineSeparator() + sb);
        }
    }
}
