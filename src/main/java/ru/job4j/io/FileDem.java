package ru.job4j.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class FileDem {
    static void p(String str) {
        System.out.println(str);
    }
    public static void main(String[] args) throws IOException {
        File f1 = new File("./data/demofile");
        p(f1.getName());
        p(f1.getPath());
        p(f1.getAbsolutePath());
        p(f1.getParent());
        p(f1.exists() ? "yes" : "no");
        p(f1.isDirectory() ? "yes. is directory" : "not directory");
        p(String.valueOf(f1.lastModified()));
        p(String.valueOf(f1.length()));
        File f2 = new File("./data/tobedeleted");
        System.out.println(f2.delete());
        File f3 = new File("./data/toberenamed");
        System.out.println(f3.renameTo(new File("./data/toberenamed3")));
        File dir = new File("./data");
        System.out.println(dir.isDirectory());
        Arrays.stream(dir.list()).forEach(System.out::println);
        String[] s = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        System.out.println("after TXT");
        Arrays.stream(s).forEach(System.out::println);
        

        /*Path src = Paths.get("./data/tobemoved");
        Path trg = Paths.get("./data/logs/tobemoved");
        Files.move(src, trg);*/
    }
}
