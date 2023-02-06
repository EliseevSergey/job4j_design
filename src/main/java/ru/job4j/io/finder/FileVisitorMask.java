package ru.job4j.io.finder;

import javax.swing.text.MaskFormatter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileVisitorMask extends SimpleFileVisitor<Path> {
    private String mask;
    private List<Path> find = new ArrayList<>();

    public FileVisitorMask(String mask) {
        this.mask = mask;
    }

    public List<Path> getFind() {
        return find;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (path.getFileName().toString().matches(mask)) {
            find.add(path);
        }
        return super.visitFile(path, attrs);
    }
}
