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
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    void add() {
        assertThat(list.size()).isEqualTo(3);
        List<Integer> mlist = new ArrayList<>();
        System.out.println(mlist);
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 1, 1};
        System.out.println("array length: " + array.length);
        List<Integer> list = new ArrayList<>();
        System.out.println("size!!! " + list.size());
        list.add(1);
        list.add(1);
        list.add(1);
        System.out.println("list size: " + list.size());
    }
}