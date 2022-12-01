package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor  extends SimpleFileVisitor<Path> {
    /*private HashMap<FileProperty, ArrayList<Path>> mainMap = new HashMap<>();
    private HashMap<FileProperty, ArrayList<Path>> duplicates = new HashMap<>();

    public HashMap<FileProperty, ArrayList<Path>> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(file.toFile().getName(), file.toFile().length());
        ArrayList<Path> paths = new ArrayList<>();
        paths.add(file);
        if (!mainMap.containsKey(fp)) {
            mainMap.put(fp, paths);
        } else {
            duplicates.put(fp, mainMap.get(fp));
            duplicates.get(fp).add(file);
        }
        return super.visitFile(file, attrs);
    }*/

    private HashMap<FileProperty, ArrayList<Path>> map = new HashMap<>();

    public HashMap<FileProperty, ArrayList<Path>> getMap() {
        return map;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fp = new FileProperty(file.toFile().getName(), file.toFile().length());
        ArrayList<Path> paths = new ArrayList<>();
        map.putIfAbsent(fp, paths);
        map.putIfAbsent(fp, paths).add(file);
        //map.putIfAbsent(fp, paths);
        return super.visitFile(file, attrs);
    }
}
