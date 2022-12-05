package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> src, File trgt) {

    }

    public void packSingleFile(File src, File trgt) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(trgt)))) {
            zip.putNextEntry(new ZipEntry(src.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(src))) {
                zip.write(out.readAllBytes());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }


}
