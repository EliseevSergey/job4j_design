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
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments are set");
        }
        Path start = Path.of(args[0]);
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toAbsolutePath()));
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("File extension is not set up");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
