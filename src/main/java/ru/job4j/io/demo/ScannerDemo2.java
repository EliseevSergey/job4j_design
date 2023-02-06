package ru.job4j.io.demo;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

public class ScannerDemo2 {
    public static void main(String[] args) throws IOException {
        String instr = "Name: Tom Age: 28 ID: 77";
        Scanner conin = new Scanner(instr);
        conin.useDelimiter("7");
        conin.findInLine("Age: ");
        if (conin.hasNext()) {
            out.println(conin.next());
        } else {
            out.println("fail");
            conin.close();
        }

        byte[] barray = "AB".getBytes(Charset.forName("Windows-1252"));
        for (byte b : barray) {
            out.println(b);
        }

        try (FileReader fileReader = new FileReader("./data/textAB.txt")) {
            StringBuilder sb = new StringBuilder();
            int simbolNum = fileReader.read();
            while (simbolNum != -1) {
                sb.append((char) simbolNum);
                simbolNum = fileReader.read();
            }
            out.println("FileReader with (char) " + System.lineSeparator() + sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("./data/textAB.txt"))) {
            StringBuilder sb = new StringBuilder();
            for (String str = br.readLine(); str != null; str = br.readLine()) {
                sb.append(str);
                sb.append(System.lineSeparator());
            }
            out.println("Buffered Reader: " + sb);
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
            out.println("File input Stream" + System.lineSeparator() + sb);
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

            out.println("Buffered input: " + System.lineSeparator() + sb);
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("./data/demoOut.txt")))) {
            out.println("one more string");
            out.printf("to be written in out %n");
            out.printf("format out %S: ", 10);
            out.printf("today is %tA", new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out2 = new PrintWriter((new FileWriter("./data/demoOut2.txt")))) {
            out2.println("one more string");
            out2.printf("to be written in out %n");
            out2.printf("format out %S: ", 10);
            out2.printf("today is %tA", new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path data = Path.of("./data");
        DirectoryStream<Path> dirStream = Files.newDirectoryStream(data);
        dirStream.forEach(System.out::println);
    }
}
