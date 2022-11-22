package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenWithoutname() {
        String path = ".pair_without_comment";
        Config cfg = new Config(path);
        cfg.load();
        assertThat(cfg.value("name")).isEqualTo("Petr Arsentev");
    }
}