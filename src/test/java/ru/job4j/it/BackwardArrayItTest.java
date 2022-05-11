package ru.job4j.it;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BackwardArrayItTest {
    @Test
    public void testHasNext() {
        BackwardArrayIt in = new BackwardArrayIt(new int[] {1, 2, 3, 4});
        assertThat(in.hasNext(), is(true));
        assertThat(in.hasNext(), is(true));
        assertThat(in.hasNext(), is(true));
        assertThat(in.hasNext(), is(true));
        assertThat(in.hasNext(), is(true));
        assertThat(in.hasNext(), is(true));
        assertThat(in.hasNext(), is(true));
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