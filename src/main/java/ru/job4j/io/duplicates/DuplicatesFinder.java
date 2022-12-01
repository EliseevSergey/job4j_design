package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path root = Path.of("/Users/admin/IdeaProjects");
        var rsl = searchDuplicate(root);
        for (FileProperty fp : rsl.keySet()) {
            System.out.println(String.format("File [%s]. Size [%s] bytes", fp.getName(), fp.getSize()));
            for (Path location : rsl.get(fp)) {
                System.out.println(String.format("Locations : [%s]", location.toAbsolutePath()));
            }
        }
    }

    public static Map<FileProperty, ArrayList<Path>> searchDuplicate(Path root) throws IOException {
        var searcher = new DuplicatesVisitor();
        Files.walkFileTree(root, searcher);
        return searcher.getMap().entrySet()
                .stream()
                .filter(i -> i.getValue().size() > 1)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
}