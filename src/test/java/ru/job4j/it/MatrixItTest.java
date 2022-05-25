package ru.job4j.it;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.NoSuchElementException;

public class MatrixItTest {
    @Test
    public void when4EI() {
        int[][] in = {{1}};
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {{}, {2}};
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}, {4}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenBegEmpty() {
        int[][] in = {
                {}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenEmpty() {
        int[][] in = {{}};
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
        }

    @Test(expected = NoSuchElementException.class)
        public void whenEmptyNext() {
        int[][] in = {{}};
        MatrixIt it = new MatrixIt(in);
        it.next();
        }

        @Test
        public void whenMultiHashNext() {
        int[][] in = {{}, {1}};
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
    }

        @Test
        public void whenNoElements() {
        int[][] in = {{}, {}, {}};
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }

    @Test
    public void whenGaps() {
        int[][] in = {
                {1}, {}, {}, {2}, {}, {}, {3}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }
}
