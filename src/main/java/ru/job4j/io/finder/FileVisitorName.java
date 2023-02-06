package ru.job4j.io.finder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileVisitorName extends SimpleFileVisitor<Path> {
    private String fileName;
    private String regEx;
    private List<Path> find = new ArrayList<>();

    public FileVisitorName(String fileName) {
        this.fileName = fileName;
    }

    public List<Path> getFind() {
        return find;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        if (fileName.equals(path.getFileName().toString())) {
            find.add(path);
        }
        return super.visitFile(path, attrs);
    }
}
