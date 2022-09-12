package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

class SmplLinkedListTest {

    private SmplLinkedList<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SmplLinkedList<Integer>();
        list.add(1);
        list.add(2);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
/*        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);*/
    }

   /* @Test
    public static void main(String[] args) {
      */
}
