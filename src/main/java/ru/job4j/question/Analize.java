package ru.job4j.question;

import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> prev, Set<User> cur) {
        Info rsl = new Info(0, 0, 0);
        Set<Integer> setIdPrev = prev.stream()
                        .map(User::getId)
                                .collect(Collectors.toSet());

        int result [] = prev.stream()
                .map(User::getId)
                .mapToInt(i->i).toArray();

        return new Info(1, 0, 0 );
    }
}
