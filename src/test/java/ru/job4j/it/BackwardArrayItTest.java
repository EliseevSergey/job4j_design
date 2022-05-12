package ru.job4j.it;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class BackwardArrayItTest {
    @Test
    public void testHasNext() {
        BackwardArrayIt in = new BackwardArrayIt(new int[]{1, 2, 3, 4});
        assertTrue(in.hasNext());
        assertTrue(in.hasNext());
        assertTrue(in.hasNext());
        assertTrue(in.hasNext());
    }

    @Test
    public void testNext() {
        BackwardArrayIt in = new BackwardArrayIt(new int[] {1, 2, 3, 4});
        assertThat(in.next(), is(4));
        assertThat(in.next(), is(3));
        assertThat(in.next(), is(2));
        assertThat(in.next(), is(1));
    }
}