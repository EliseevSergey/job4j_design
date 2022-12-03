package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Path.of(args[0]);
        String extension = args[1];
        search(start, p -> p.toFile()
                .getName()
                .endsWith(extension))
                .forEach(System.out::println);
    }

    public static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        Path start = Path.of(args[0]);
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("File [%s] not exist ", args[0]));
        }
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("File [%s] is not directory", args[0]));
        }

        if (!args[1].startsWith(".") || args[1].length() == 1) {
            throw new IllegalArgumentException(String.format("File extension [%s] is incorrect", args[1]));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
