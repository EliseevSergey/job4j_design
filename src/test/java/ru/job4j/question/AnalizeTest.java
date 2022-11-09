package ru.job4j.question;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class AnalizeTest {
    @Test
    void whenNoChang() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u666 = new User(3, "C");
        Set prev = Set.of(u1, u2, u3);
        Set cur = Set.of(u1, u2, u3);
        assertThat(Analize.diff(prev, cur)).isEqualTo(new Info(0, 0, 0));
    }

    @Test
    void whenTwoChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u2Edited = new User(2, "BB");
        User u3Edited = new User(3, "CC");
        Set prev = Set.of(u1, u2, u3);
        Set cur = Set.of(u1, u2Edited, u3Edited);
        assertThat(Analize.diff(prev, cur)).isEqualTo(new Info(0, 2, 0));
    }

    @Test
    void whenFourDel() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "D");
        User u5 = new User(5, "E");
        User u6 = new User(6, "F");
        Set prev = Set.of(u1, u2, u3, u4, u5, u6);
        Set cur = Set.of(u1, u5);
        assertThat(Analize.diff(prev, cur)).isEqualTo(new Info(0, 0, 4));
    }

    @Test
    void whenThreeDelAndTwoAddedAndOneChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "D");
        User u5 = new User(5, "E");
        User u6 = new User(6, "F");
        Set prev = Set.of(u1, u2, u3, u4);
        Set cur = Set.of(u5, u6);
        assertThat(Analize.diff(prev, cur)).isEqualTo(new Info(2, 0, 4));
    }

    @Test
    void whenAllDelAndNothingAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "D");
        Set prev = Set.of(u1, u2, u3, u4);
        Set cur = Set.of();
        assertThat(Analize.diff(prev, cur)).isEqualTo(new Info(0, 0, 4));
    }

        @Test
    void whenAllChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set previous = Set.of(u1, u2, u3);
        Set current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(1, 1, 1));
    }
}