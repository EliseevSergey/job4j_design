package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path root = Path.of(".");
        var rsl = searchDuplicate(root);
        for (FileProperty fp : rsl.keySet()) {
            System.out.println(String.format("File [%s]. Size [%s] bytes", fp.getName(), fp.getSize()));
            for (Path location : rsl.get(fp)) {
                System.out.println(String.format("Locations : [%s]", location.toAbsolutePath()));
            }
        }
    }

    public static HashMap<FileProperty, ArrayList<Path>> searchDuplicate(Path root) throws IOException {
        var searcher = new DuplicatesVisitor();
        Files.walkFileTree(root, searcher);
        return searcher.getMap();
    }
}