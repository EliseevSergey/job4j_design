package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;

class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;

    @BeforeEach
    public void initData() {
        linked = new ForwardLinked<Integer>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
    }

    @Test
    void delFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
    }

    @Test
    void nothingToDel {

    }
}
