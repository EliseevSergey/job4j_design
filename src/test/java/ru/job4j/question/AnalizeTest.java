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
    void whenOneChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(2, "D");
        Set prev = Set.of(u1, u2, u3);
        Set cur = Set.of(u1, u4, u3);
        assertThat(Analize.diff(prev, cur)).isEqualTo(new Info(0, 1, 0));
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
    void whenAllDelAndNewAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "D");
        User u5 = new User(5, "E");
        User u6 = new User(6, "F");
        Set prev = Set.of(u1, u2, u3, u4);
        Set cur = Set.of(u5, u6);
        assertThat(Analize.diff(prev, cur)).isEqualTo(new Info(0, 0, 4));
    }

}