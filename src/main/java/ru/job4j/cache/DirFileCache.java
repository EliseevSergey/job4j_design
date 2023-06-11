package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
        System.out.println(String.format("CACHED DIR HAS BEEN CREATED, LOCATION IS %s", cachingDir));
    }

    @Override
    protected String load(String key) {
        StringJoiner sj = new StringJoiner("/");
        sj.add(cachingDir).add(key);
        String path = sj.toString();

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("UTF-8")))) {
            for (String str = br.readLine(); str != null; str = br.readLine()) {
                sb.append(str);
                sb.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String loaded = sb.toString();
        put(key, loaded);
        return loaded;
    }
}