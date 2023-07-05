package ru.job4j.ood.lsp.example;

public class BiggerSister extends Baby {
    private String familyNumber;

    @Override
    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }

    public BiggerSister(int age) {
        super(age);
    }

    public BiggerSister(int ageMonths, String familyNumber) {
        super(ageMonths);
        this.familyNumber = familyNumber;
    }

    @Override
    public Sound speak(int age) {
        Sound rsl = null;
        if (age <= 7) {
            throw new IllegalArgumentException("Too little to speak");
        } else {
            rsl = new Sound("Some words");
        }
        return rsl;
    }

    @Override
    public Document showDocument(String years) {
        return new Document("Zagran pasport");
    }
}
