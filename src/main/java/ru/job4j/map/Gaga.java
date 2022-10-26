package ru.job4j.map;

import java.util.Objects;

public class Gaga {
    private int lol;
    private String name;

    public Gaga(int lol, String name) {
        this.lol = lol;
        this.name = name;
    }

    public int getLol() {
        return lol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gaga gaga = (Gaga) o;
        return lol == gaga.lol && Objects.equals(name, gaga.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lol, name);
    }
}
