package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void nothingToParse() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void noEqualSimbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"IvanIvanov"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("=");
    }

    @Test
    void startWithEqual() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"=Ivanov"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("key");
    }

    @Test
    void nothingExceptEqual() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"Ivan="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(names[0])
                .hasMessageContaining("value");
    }
}