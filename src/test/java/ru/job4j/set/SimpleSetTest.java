package ru.job4j.set;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleSetTest {
    private SimpleSet<Integer> set;

    @BeforeEach
    void setUp() {
        set = new SimpleSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
    }

    @Test
    void whenAddSame() {
        set.add(3);
        set.add(2);
        assertThat(set).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddNonNull() {
        assertThat(set.add(4)).isTrue();
        assertThat(set.contains(4)).isTrue();
        assertThat(set.add(4)).isFalse();
    }

    @Test
    void whenAddNull() {
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }
}