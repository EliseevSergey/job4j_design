package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor  extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, ArrayList<Path>> hashMap = new HashMap<>();

    public HashMap<FileProperty, ArrayList<Path>> getMap() {
        return hashMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(file.toFile().getName(), file.toFile().length());
        ArrayList<Path> paths = new ArrayList<>();
        hashMap.putIfAbsent(fp, paths);
        hashMap.get(fp).add(file);
        return super.visitFile(file, attrs);
    }
}