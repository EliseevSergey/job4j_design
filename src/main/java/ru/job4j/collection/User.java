package ru.job4j.collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "name='" + "User{" + name + '\'' + ", children=" + children + '}';
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();

        User first = new User("Ivan", 2, birthday);
        int hashCode1 = first.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;

        User second = new User("Ivan", 2, birthday);
        int hashCode2 = second.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;

        map.put(first, new Object());
        map.put(second, new Object());
        map.put(null, new Object());
        map.keySet();
        System.out.println(first.equals(second));
        System.out.println(first.birthday.equals(second.birthday));
        System.out.println(hashCode1 == hashCode2);
        System.out.printf(" first - хэшкодКлюча: %s,  хэшМапы(результат работы хэшФункции мапы): %s,  бакет=индекс: %s ", hashCode1, hash1, bucket1);
        System.out.println("");
        System.out.printf("second - хэшкодКлюча: %s,  хэшМапы(результат работы хэшФункции мапы): %s,  бакет=индекс: %s ", hashCode2, hash2, bucket2);
        System.out.println("");
        for (User usr : map.keySet()) {
            System.out.println(usr);
        }
    }
}
