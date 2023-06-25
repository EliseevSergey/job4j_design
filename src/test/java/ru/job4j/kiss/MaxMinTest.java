package ru.job4j.kiss;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxMinTest {
    @Test
    public void max() {
        List<Integer> in = new ArrayList<>();
        in.add(3);
        in.add(2);
        in.add(1);
        in.add(0);
        MaxMin mm = new MaxMin();
        Comparator<Integer> cmp = (o1, o2) -> o1 - o2;
        assertEquals(3, mm.maxOnBase(in, cmp));
    }

    @Test
    public void whenEmpty() {
        List<Integer> in = new ArrayList<>();
        MaxMin mm = new MaxMin();
        Comparator<Integer> cmp = (o1, o2) -> o1 - o2;
        assertNull(mm.maxOnBase(in, cmp));
        assertNull(mm.minOnBase(in, cmp));
    }

    @Test
    public void whenSizeOne() {
        List<Integer> in = new ArrayList<>();
        in.add(666);
        MaxMin mm = new MaxMin();
        Comparator<Integer> cmp = (o1, o2) -> o1 - o2;
        assertEquals(666, mm.maxOnBase(in, cmp));
        assertEquals(666, mm.minOnBase(in, cmp));
    }

    @Test
    void minSecond() {
        List<Integer> in = new ArrayList<>();
        in.add(3);
        in.add(2);
        in.add(1);
        in.add(0);
        MaxMin mm = new MaxMin();
        Comparator<Integer> cmp = (o1, o2) -> o1 - o2;
        assertEquals(0, mm.minOnBase(in, cmp));
    }
}