package ru.job4j.question;

import org.junit.jupiter.api.Test;

import java.util.Set;

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
    void whenOneDel() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set prev = Set.of(u1, u2, u3);
        Set cur = Set.of(u1, u2);
        assertThat(Analize.diff(prev, cur)).isEqualTo(new Info(0, 0, 1));

    }


}