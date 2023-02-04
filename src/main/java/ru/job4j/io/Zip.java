package ru.job4j.io;

import ru.job4j.io.finder.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<Path> src, Path trgt) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(trgt.toFile())))) {
            for (Path path : src) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private static void validation(ArgsName argsName) {
            if (argsName.get("d").isEmpty()) {
                throw new IllegalArgumentException("Argument missing. Source is not defined.");
            }
            if (argsName.get("e").isEmpty()) {
                throw new IllegalArgumentException("Argument missing. Excluded extension is not defined.");
            }
            if (argsName.get("o").isEmpty()) {
                throw new IllegalArgumentException("Argument missing. Result file is not defined.");
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
        if (args.length != 3) {
            throw new IllegalArgumentException("Not all arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validation(argsName);
        try {
            packFiles(Search.search(Path.of(argsName.get("d")), f -> !f
                    .toFile()
                    .getName()
                    .endsWith(argsName.get("e"))), Path.of(argsName.get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}