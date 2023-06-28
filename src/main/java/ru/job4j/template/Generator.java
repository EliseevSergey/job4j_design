package ru.job4j.template;

import java.util.Map;

public interface Generator {
    String producer(String template, Map<String, String> args);
}
