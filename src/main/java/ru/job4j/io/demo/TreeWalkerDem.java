package ru.job4j.io.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class TreeWalkerDem {
    public static void main(String[] args) throws IOException {
        Path path = Path.of(".");
        File file = new File("./data");
        File[] files = file.listFiles();
        String[] filesAsStr = file.list();
        Stream<String> paths = Files.lines(path);
        DirectoryStream<Path> pathstream = Files.newDirectoryStream(path);
        Files.walkFileTree(path, new FileWalker());
    }
}
