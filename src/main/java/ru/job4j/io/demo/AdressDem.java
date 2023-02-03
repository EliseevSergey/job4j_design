package ru.job4j.io.demo;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class AdressDem {
    public static void main(String[] args) {
        Path pathMain = Path.of("D:", "Users");
        Path path = FileSystems.getDefault().getPath("D:", "Users");
        System.out.println(path);
        String pathSeparator = FileSystems.getDefault().getSeparator();
        System.out.println(pathSeparator);
        System.out.println(File.separator);
        Path pathTarget = Path.of("D:\\Users\\me\\folder1\\NeededFile.txt");
        Path patSrc = Path.of("D:\\Users\\me\\folder2\\folder2-2");
        Path ph = Path.of("..\\..\\folder1\\NeededFile.txt");
        System.out.println(pathTarget.relativize(patSrc));
        System.out.println(patSrc.relativize(pathTarget));

    }

}
