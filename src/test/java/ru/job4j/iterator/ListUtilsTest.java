package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3)
                .containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        input.add(2);
        input.add(4);
        input.add(5);
        input.add(6);
        input.add(7);
        ListUtils.removeIf(input, item -> item > 4);
        assertThat(input).hasSize(4)
                .containsSequence(1, 3, 2, 4);
    }

    @Test
    void whenRemoveIfDuplicate() {
        input.add(2);
        input.add(4);
        input.add(5);
        input.add(5);
        ListUtils.removeIf(input, item -> item > 4);
        assertThat(input).hasSize(4)
                .containsSequence(1, 3, 2, 4);
    }



    @Test
    void whenReplaceIf() {
        input.add(2);
        input.add(4);
        input.add(5);
        input.add(6);
        ListUtils.replaceIf(input, item -> item > 4, 666);
        assertThat(input).hasSize(6)
                .containsSequence(1, 3, 2, 4, 666, 666);
    }

    @Test
    void whenRemoveAllDoubleLoop() {
        input.add(2);
        input.add(4);
        input.add(5);
        input.add(6);
        List<Integer> delete = new ArrayList<Integer>(Arrays.asList(4, 5, 666, 777, 9999));
        ListUtils.removeAllDoubleLoop(input, delete);
        assertThat(input).hasSize(4)
                .containsSequence(1, 3, 2, 6);
    }

    @Test
    void whenRemoveAll() {
        input.add(2);
        input.add(4);
        input.add(5);
        input.add(6);
        List<Integer> delete = new ArrayList<Integer>(Arrays.asList(4, 5, 666, 777, 9999));
        ListUtils.removeAll(input, delete);
        assertThat(input).hasSize(4)
                .containsSequence(1, 3, 2, 6);
    }
}