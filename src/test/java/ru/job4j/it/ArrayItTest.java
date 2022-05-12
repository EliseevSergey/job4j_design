package ru.job4j.it;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayItTest {
    @Test
    public void whenMultiCallhasNextThenTrue() {
        ArrayIt it = new ArrayIt(new int[] {1, 2, 3});
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenReadSequence() {
        ArrayIt it = new ArrayIt(new int[] {1, 2, 3});
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }
}