package ru.job4j.io;

import ru.job4j.io.finder.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not all arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validation(argsName);
        try {
            CSVReader.handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handle(ArgsName argsName) {
        String delimiter = argsName.get("delimiter");
        File src = new File(argsName.get("path"));
        try {
            Scanner input = new Scanner(src);
            input.useDelimiter(System.lineSeparator());
            List<String> header = Arrays.stream(input.next().split(delimiter)).toList();
            try {
                Scanner fl = new Scanner(argsName.get("filter"));
                fl.useDelimiter(",");
                List<String> filters = new ArrayList<>();
                while (fl.hasNext()) {
                filters.add(fl.next());
                }
                List<Integer> fltrIndex = new ArrayList<>();
                filters.forEach(i -> fltrIndex.add(header.indexOf(i)));
                String stdout = "stdout";
                try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))));
                    Scanner sc = new Scanner(src)) {
                    sc.useDelimiter(System.lineSeparator());
                    StringBuilder sb = new StringBuilder();
                    while (sc.hasNext()) {
                    String[] line = sc.next().split(delimiter);
                    fltrIndex.forEach(i -> sb.append(line[i]).append(delimiter));
                    sb.replace(sb.length() - 1, sb.length(), System.lineSeparator());
                    }
                    if (!stdout.equals(argsName.get("out"))) {
                    pw.write(sb.toString());
                    } else {
                    System.out.println(sb);
                        }
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(ArgsName argsName) {
        Path src = Path.of(argsName.get("path"));
        if (!src.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Source file [%s] not exist ", src));
        }

        if (argsName.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Delimiter argument missing");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Filter missing");
        }
        Path out = Path.of(argsName.get("path"));
        String stdout = "stdout";
        if (argsName.get("out").isEmpty()) {
            throw new IllegalArgumentException("Out file missing");
        }
        if (!out.toFile().exists() && !stdout.equals(argsName.get("out"))) {
            throw new IllegalArgumentException("Out is not defined correctly");
        }
    }
}


