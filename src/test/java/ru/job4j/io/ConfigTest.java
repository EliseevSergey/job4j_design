package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config cfg = new Config(path);
        System.out.println(cfg);
        cfg.load();
        assertThat(cfg.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenBifFile() {
        String path = "./data/app.properties";
        Config cfg = new Config(path);
        System.out.println(cfg);
        cfg.load();
        assertThat(cfg.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenNoKey() {
        String path = "./data/noKey.properties";
        Config cfg = new Config(path);
        System.out.println(cfg);
        assertThatThrownBy(cfg::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoValue() {
        String path = "./data/noValue.properties";
        Config cfg = new Config(path);
        System.out.println(cfg);
        assertThatThrownBy(cfg::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEqualInValue() {
        String path = "./data/EqualInValue.properties";
        Config cfg = new Config(path);
        System.out.println(cfg);
        cfg.load();
        assertThat(cfg.value("key")).isEqualTo("value=1");
    }

    @Test
    void whenNoEqual() {
        String path = "./data/NoEqual.properties";
        Config cfg = new Config(path);
        System.out.println(cfg);
        assertThatThrownBy(cfg::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenComentsAndBlankLines() {
        String path = "./data/ComentsAndBlanks.properties";
        Config cfg = new Config(path);
        System.out.println(cfg);
        cfg.load();
        assertThat(cfg.value("lastKeyInFile")).isEqualTo("lastValue");
    }
}