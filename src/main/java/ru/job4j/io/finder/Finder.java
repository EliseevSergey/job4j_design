package ru.job4j.io.finder;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Finder {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not all arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        validation(argsName);
        StringBuilder sb = new StringBuilder();
        List<Path> rsl = new ArrayList<>();

        if ("name".equals(argsName.get("t"))) {
            rsl = findByName(Path.of(argsName.get("d")), argsName.get("n"));
        }

        if ("regex".equals(argsName.get("t"))) {
            rsl = findByRegEx(Path.of(argsName.get("d")), argsName.get("n"));
        }

        if ("mask".equals(argsName.get("t"))) {
            rsl = findByMask(Path.of(argsName.get("d")), argsName.get("n"));
        }

        sb.append(String.format("%s : files were found with [%s] search type with key [%s] in folder [%s] \n",
                rsl.size(), argsName.get("t"), argsName.get("n"), argsName.get("d")));
        try (PrintWriter pw = new PrintWriter(argsName.get("o"))) {
            rsl.stream()
                    .map(p -> p.toString())
                    .forEach(s -> sb.append(s).append(System.lineSeparator()));
            pw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> findByName(Path src, String fileName) {
        FileVisitorName fvName = new FileVisitorName(fileName);
        try {
            Files.walkFileTree(src, fvName);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return fvName.getFind();
    }

    private static List<Path> findByRegEx(Path src, String regEx) {
        FileVisitorRegEx fvRegEx = new FileVisitorRegEx(regEx);
        try {
            Files.walkFileTree(src, fvRegEx);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return fvRegEx.getFind();
    }

    private static List<Path> findByMask(Path src, String mask) {
        FileVisitorMask fvMask = new FileVisitorMask(mask);
        try {
            Files.walkFileTree(src, fvMask);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return fvMask.getFind();
    }

    private static void validation(ArgsName argsName) {
        if (argsName.get("d").isEmpty()) {
            throw new IllegalArgumentException("Argument missing. Source is set up.");
        }
        if (argsName.get("n").isEmpty()) {
            throw new IllegalArgumentException("Argument missing. Name/Mask/RegEx is not set up.");
        }
        if (argsName.get("t").isEmpty()) {
            throw new IllegalArgumentException("Argument missing. Type of finder is not set up.");
        }
        if (argsName.get("o").isEmpty()) {
            throw new IllegalArgumentException("Argument missing. Output file is not set up.");
        }
        Path src = Path.of(argsName.get("d"));
        if (!src.toFile().exists()) {
            throw new IllegalArgumentException(String.format("File [%s] not exist ", src));
        }
        if (!src.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("File [%s] is not directory", src));
        }
    }
}

