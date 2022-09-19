package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void nothingToDel() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThatThrownBy(linked::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiDel() {
        linked.deleteFirst();
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.next()).isEqualTo(4);
    }

    @Test
    void whenItComesToTail() {
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.next()).isEqualTo(4);
        assertThatThrownBy(it :: next).isInstanceOf(NoSuchElementException.class);
    }
}
