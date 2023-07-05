package ru.job4j.ood.lsp.example;

public class LittleSister extends BiggerSister {
    public LittleSister(int age) {
        super(age);
    }

    @Override
    public Sound speak(int age) {
        Sound rsl = null;
        if (age <= 12) {
            throw new IllegalArgumentException("Too little to speak");
        } else {
            rsl = new Sound("Some words");
        }
        return rsl;
    }
}
