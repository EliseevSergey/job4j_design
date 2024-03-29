package ru.job4j.it;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EvenIteratorTest {
    private Iterator<Integer> it;
    private Iterator<Integer> evens;

    @Before
    public void setUp() {
        it = new EvenNumbersIterator(new int[] {1, -3, 3, 2, 3, 4, 5, 6, 7});
        evens = new EvenNumbersIterator(new int[] {2, 4, 6, 8, 10, 12, 14, 16, 18});
    }

    @Test
    public void onlyEven() {
        assertThat(evens.hasNext(), is(true));
        assertThat(evens.hasNext(), is(true));
        assertThat(evens.hasNext(), is(true));
        assertThat(evens.hasNext(), is(true));
        assertThat(evens.hasNext(), is(true));
        assertThat(evens.next(), is(2));
        assertThat(evens.next(), is(4));
        assertThat(evens.next(), is(6));
        assertThat(evens.next(), is(8));
        assertThat(evens.next(), is(10));
    }

    @Test(expected = NoSuchElementException.class)
    public void returnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    @Test
    public void  returnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void trueSingleEven() {
        it = new EvenNumbersIterator(new int[]{10});
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void allNumbersEven() {
        it = new EvenNumbersIterator(new int[] {2, 4, 6});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void remove() {
        int[] in = new int[] {6, 6, 6};
        it = new EvenNumbersIterator(in);
        it.remove();
        int[] exp = new int[] {0, 6, 6};
        assertThat(exp, is(in));
    }

    @Test
    public void forEachRemain() {
        int[] in = new int[]{6, 8, 10};
        it = new EvenNumbersIterator(in);
        it.forEachRemaining(System.out::println);
    }
}

