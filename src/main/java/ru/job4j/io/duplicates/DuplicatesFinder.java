package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path start = Path.of(".");
        var file = new FileProperty(7, "FindMeCopy.txt");
        search(start,  p -> (p.toFile()
                .getName()
                .equals(file.getName())) && (p.toFile().length() == file.getSize()),
                 file);
    }

    public static List<Path> search(Path root, Predicate<Path> condition, FileProperty fileProp) throws IOException {
        System.out.println(String.format("File [%s]. Size [%s] bytes", fileProp.getName(), fileProp.getSize()));
        var searcher = new DuplicatesVisitor(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getList();
    }
}
