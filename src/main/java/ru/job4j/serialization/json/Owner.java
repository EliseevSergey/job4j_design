package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Owner {
    private String surname;
    private Cat[] pets;
    private int age;
    boolean vip;

    public Owner(String surname, Cat[] pets, int age, boolean vip) {
        this.surname = surname;
        this.pets = pets;
        this.age = age;
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Owner{"
                + "surname='" + surname + '\''
                + ", pets=" + Arrays.toString(pets)
                + ", age=" + age
                + ", vip=" + vip
                + '}';
    }

    public static void main(String[] args) {
        Owner petrov = new Owner("Petrov",
                new Cat[]{new Cat("Barsik", 2, "Grey"),
                        new Cat("Vasya", 1, "Black")}, 33, true);
        Gson gson = new GsonBuilder().create();
        String gsonedPetrov = gson.toJson(petrov);
        System.out.println(gsonedPetrov);

        String ownerJson = "{\"surname\":\"Sidorov\",\"pets\":[{\"name\":\"Marsik\",\"age\":2,\"color\":\"Grey\"},"
                + "{\"name\":\"Pushok\",\"age\":1,\"color\":\"White\"}],\"age\":33,\"vip\":true}";
        Owner fromJsonString = gson.fromJson(ownerJson, Owner.class);
        System.out.println(fromJsonString);
    }
}