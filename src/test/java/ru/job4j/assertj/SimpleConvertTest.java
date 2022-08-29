package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        var sc = new SimpleConvert();
        String[] result = sc.toArray(new String[]{"One", "Two", "Three"});
        assertThat(result).hasSize(3)
                .contains("Two")
                .contains("One", atIndex(0))
                .containsAnyOf("seven", "Three")
                .doesNotContain("one", Index.atIndex(1));
    }

    @Test
    void checklist() {
        var sc = new SimpleConvert();
        List<String> rsl = sc.toList(new String[]{"First", "Second", "Third", "Fourth", "Fifth"});
        assertThat(rsl).filteredOn(e -> e.startsWith("F"))
                .contains("Fifth")
                .doesNotContain("Fifteen");
    }

    @Test
    void checkSet() {
        var sc = new SimpleConvert();
        Set<Integer> rsl = sc.toSet(new Integer[]{1, 2, 2,  3, 3, 4, 5});
        assertThat(rsl).isNotEmpty()
                .containsSequence(1, 2, 3)
                .allSatisfy(e -> assertThat(e).isLessThan(10))
                .filteredOn(e -> e > 3 & e < 10).contains(4, 5);
    }

    @Test
    void checkMap() {
        var sc = new SimpleConvert();
        var i = new AtomicInteger(5);
        Map<String, Integer> rsl = sc.toMap(new String[]{"First", "Second", "Third"});
        for (String a : rsl.keySet()) {
            System.out.println("Key: " + a + " Value: " + rsl.get(a));
        }
        assertThat(rsl).hasSize(3)
                .containsKeys("First", "Second", "Third")
                .containsValues(1, 3, 2)
                .doesNotContainValue(4);
    }
}