package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path dir = Path.of("./data/dir/paths");
        Files.createDirectories(dir);
        Path pathToFile = Path.of("./data/dir/paths/new.txt");
        Files.createFile(pathToFile);
        System.out.println("Файл/директория существует?: " + Files.exists(pathToFile));
        System.out.println("Это директория?: " + Files.isDirectory(pathToFile));
        System.out.println("Это файл?: " + Files.isRegularFile(pathToFile));
        System.out.println("Имя файла: " + pathToFile.getFileName());
        System.out.println("Путь к файлу абсолютный?: " + pathToFile.isAbsolute());
        System.out.println("Родительская директория файла: " + pathToFile.getParent());
        System.out.println("Абсолютный путь к файлу: " + pathToFile.toAbsolutePath());
        System.out.println("Абсолютный путь к директории: " + dir.toAbsolutePath());
        System.out.println("Доступен для чтения?: " + Files.isReadable(pathToFile));
        System.out.println("Доступен для записи?: " + Files.isWritable(pathToFile));
        Path trg = Path.of("./data/trash");
        Path trgIn = Path.of("./data/trash/newNEW.txt");
        Files.createDirectories(trg);
        Files.move(pathToFile, trgIn);
    }
}
