package ru.job4j.ood.tdd;

import java.util.List;
import java.util.function.Predicate;

public interface Account {
    List<Ticket> find(Predicate<Ticket> filter);

}
