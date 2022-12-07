package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<Path> src, Path trgt) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(trgt.toFile().getAbsolutePath())))) {
            for (Path path : src) {
                zip.putNextEntry(new ZipEntry(path.toFile().getName()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile().getAbsolutePath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static void validation(ArgsName argsName) {
            System.out.println(argsName.get("d"));
            System.out.println(argsName.get("e"));
            System.out.println(argsName.get("o"));
            if (argsName.get("d").isEmpty()) {
                throw new IllegalArgumentException("Not enough arguments. Source is not defined.");
            }
            if (argsName.get("e").isEmpty()) {
                throw new IllegalArgumentException("Not enough arguments. Excluded extension is not defined");
            }
            if (argsName.get("o").isEmpty()) {
                throw new IllegalArgumentException("Not enough arguments. Result file is not defined");
            }

            Path start = Path.of(argsName.get("d"));
            if (!start.toFile().exists()) {
                throw new IllegalArgumentException(String.format("File [%s] not exist ", start));
            }
            if (!start.toFile().isDirectory()) {
                throw new IllegalArgumentException(String.format("File [%s] is not directory", start));
            }
        }

    public static void main(String[] args) {
        validation(ArgsName.of(args));
        try {
            packFiles(Search.search(Path.of(ArgsName.of(args).get("d")), f -> !f
                    .toFile()
                    .getName()
                    .endsWith(ArgsName.of(args).get("e"))), Path.of(ArgsName.of(args).get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}