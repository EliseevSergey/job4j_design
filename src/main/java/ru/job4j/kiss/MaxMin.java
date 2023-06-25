package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    static <T> T base(List<T> value, BiPredicate<T, T> biPredicate) {
        T rsl;
        if (value.size() == 0) {
            rsl = null;
            System.out.println("List is empty");
        } else {
            rsl = value.get(0);
            for (int i = 0; i < value.size() - 1; i++) {
                boolean test = biPredicate.test(rsl, value.get(i + 1));
                rsl = (test) ? rsl : value.get(i + 1);
            }
        }
        return rsl;
    }

    public <T> T minOnBase(List<T> value, Comparator<T> comparator) {
        return base(value, ((a, b) -> comparator.compare(a, b) < 0));
    }

    public <T> T maxOnBase(List<T> value, Comparator<T> comparator) {
        return base(value, ((a, b) -> comparator.compare(a, b) >= 0));
    }
}