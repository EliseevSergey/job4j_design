package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SimpleArrayListTest {
    private SimpleList<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleArrayList<>(2);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
    }

/*    @Test
    void checkIterator() {
        assertThat(list.size()).isEqualTo(3);
        assertThat(list).hasSize(3);
    }*/

    @Test
    void whenAddThenSizeIncrease() {
        list.add(4);
        assertThat(list.size()).isEqualTo(9);
    }
}