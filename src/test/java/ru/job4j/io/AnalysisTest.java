package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    void withRealSource(@TempDir Path tempDir) throws IOException {
        File target = tempDir.resolve("target.txt").toFile();
        Analysis failPeriod = new Analysis();
        failPeriod.unavailable("./data/server.txt", target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:59:0111:01:02;11:02:02").isEqualTo(rsl.toString());
    }

    @Test
    void withTempSource(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (BufferedReader in = new BufferedReader(new FileReader("./data/server.txt"));
                PrintWriter out = new PrintWriter(source)) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                out.println(line);
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis failPeriod = new Analysis();
        failPeriod.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;10:59:0111:01:02;11:02:02").isEqualTo(rsl.toString());
    }
}
