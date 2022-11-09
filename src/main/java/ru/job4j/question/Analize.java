package ru.job4j.question;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> prev, Set<User> cur) {
        Info rsl = new Info(0, 0, 0);
        Set prevSetId = prev.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        Set curSetId = cur.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        int sizeCur = curSetId.size();
        Iterator<User> prevItr = prev.iterator();
        int changed = 0;
        while (prevItr.hasNext()) {
            User user = prevItr.next();
            if (!cur.contains(user)) {
                if (curSetId.contains(user.getId())) {
                    changed++;
                }
            }
        }
        rsl.setChanged(changed);
        curSetId.retainAll(prevSetId);
        rsl.setAdded(sizeCur - curSetId.size());
        rsl.setDeleted(prevSetId.size() - curSetId.size());
        return rsl;
    }
}