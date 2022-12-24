package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        File src = new File(argsName.get("path"));
        Scanner input = new Scanner(src);
        input.useDelimiter(System.lineSeparator());
        List<String> header = Arrays.stream(input.next().split(delimiter)).toList();
        Scanner fl = new Scanner(argsName.get("filter"));
        fl.useDelimiter(",");
        List<String> filters = new ArrayList<>();
        while (fl.hasNext()) {
            filters.add(fl.next());
        }
        List<Integer> fltrIndex = new ArrayList<>();
        filters.forEach(i -> fltrIndex.add(header.indexOf(i)));
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))));
           Scanner sc = new Scanner(src)) {
            sc.useDelimiter(System.lineSeparator());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                String[] line = sc.next().split(";");
                fltrIndex.forEach(i -> sb.append(line[i] + delimiter));
                sb.replace(sb.length() - 1, sb.length(), System.lineSeparator());
                }
            pw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(String[] args) {
        System.out.println(args.length);
        if (args.length != 2) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        Path source = Path.of(args[0]);
        if (!source.toFile().exists()) {
            throw new IllegalArgumentException(String.format("File [%s] not exist ", args[0]));
        }
        if (!source.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("File [%s] is not directory", args[0]));
        }
        Path out = Path.of(args[2]);
        String stdout = "stdout";
        if (!stdout.equals(args[2]) && !out.toFile().isDirectory()) {
                throw new IllegalArgumentException(String.format("Out [%s] is incorrect", args[2]));
            }
        }
}

