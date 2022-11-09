package ru.job4j.question;

import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> prev, Set<User> cur) {
        Info rsl = new Info(0, 0, 0);

        Set prevSetId = prev.stream()
                .map(User::getId)
                .collect(Collectors.toSet());

        int sizePrev = prevSetId.size();

        Set curSetId = cur.stream()
                .map(User::getId)
                .collect(Collectors.toSet());

        curSetId.retainAll(prevSetId);

        int switcher = sizePrev - curSetId.size(); //остануться только общие элементы

        if (switcher >= 0) {
            rsl.setDeleted(switcher);
        } else {
            rsl.setAdded(Math.abs(switcher));
        }

        return rsl;
    }
}