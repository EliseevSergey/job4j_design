package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DuplicatesVisitor  extends SimpleFileVisitor<Path> {
    private Predicate<Path> fltr;
    private List<Path> list = new ArrayList<>();

    public DuplicatesVisitor(Predicate<Path> fltr) {
        this.fltr = fltr;
    }

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (fltr.test(file)) {
            list.add(file);
            System.out.println(String.format("Locations : [%s]", file.getParent().toAbsolutePath()));
        }

        return super.visitFile(file, attrs);
    }
}
