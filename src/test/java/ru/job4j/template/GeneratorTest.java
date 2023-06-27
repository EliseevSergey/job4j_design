package ru.job4j.template;

import org.junit.jupiter.api.Test;
import ru.job4j.map.Map;
import java.util.Iterator;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

@Disabled
public class GeneratorTest {
    @Test
    public void whenInput() {
        Generator generator = new Generator() {
            @Override
            public String producer(String template, Map<String, String> args) {
                return null;
            }
        };
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> in = new Map<>() {
            @Override
            public boolean put(String key, String value) {
                return false;
            }

            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public boolean remove(String key) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }
        };
        assertEquals("I am a Petr Arsentev, Who are you? ", generator.producer(template, in));
    }

    @Test
    public  void whenNoKeyInMapThenExeption() {
        String template = "Hello ${name}, on behalf of ${unknownKey} side I'm glad to inform you. ";
        Map<String, String> in = new Map<>() {
            @Override
            public boolean put(String key, String value) {
                return false;
            }

            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public boolean remove(String key) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }
        };
        in.put("name", "Petr Arsentev");
        Generator generator = new Generator() {
            @Override
            public String producer(String template, Map<String, String> args) {
                return null;
            }
        };
        assertThatThrownBy(() -> generator.producer(template, in))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("Unknown key in template [%s] ", template));
    }

    @Test
    public  void whenUselessKeyInMapThenExeption() {
        Generator generator = new Generator() {
            @Override
            public String producer(String template, Map<String, String> args) {
                return null;
            }
        };
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> in = new Map<>() {
            @Override
            public boolean put(String key, String value) {
                return false;
            }

            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public boolean remove(String key) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }
        };
        in.put("name", "Petr Arsentev");
        in.put("subject", "you");
        in.put("company", "Big Bro Company");
        assertThatThrownBy(() -> generator.producer(template, in))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("Value [%s] was not used", in.get("company")));
    }
}
