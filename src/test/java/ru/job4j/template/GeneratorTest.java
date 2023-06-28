package ru.job4j.template;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

@Disabled
public class GeneratorTest {
    private TextGenerator textGenerator = new TextGenerator();

    @Test
    public void whenInput() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> in = new HashMap<>();
        in.put("name", "Petr Arsentev");
        assertEquals("I am a Petr Arsentev, Who are you? ", textGenerator.producer(template, in));
    }

    @Test
    public  void whenNoKeyInMapThenExeption() {
        String template = "Hello ${name}, on behalf of ${unknownKey} side I'm glad to inform you. ";
        Map<String, String> in = new HashMap<>();
        in.put("name", "Petr Arsentev");
        assertThatThrownBy(() -> textGenerator.producer(template, in))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("Unknown key in template [%s] ", template));
    }

    @Test
    public  void whenUselessKeyInMapThenExeption() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> in = new HashMap<>();
        in.put("name", "Petr Arsentev");
        in.put("subject", "you");
        in.put("company", "Big Bro Company");
        assertThatThrownBy(() -> textGenerator.producer(template, in))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("Value [%s] was not used", in.get("company")));
    }
}
