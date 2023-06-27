package ru.job4j.template;

import ru.job4j.map.Map;

public interface Generator {
    String producer(String template, Map<String, String> args);
}
