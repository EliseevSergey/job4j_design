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

public class FileVisitorRegEx extends SimpleFileVisitor<Path> {
    private String regEx;
    private List<Path> find = new ArrayList<>();

    public FileVisitorRegEx(String regEx) {
        this.regEx = regEx;
    }

    public List<Path> getFind() {
        return find;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        Pattern regExPat = Pattern.compile(regEx);
        Matcher regExMatch = regExPat.matcher(path.getFileName().toString());
        if (regExMatch.find()) {
            find.add(path);
        }
        return super.visitFile(path, attrs);
    }
}