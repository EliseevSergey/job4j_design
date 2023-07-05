package ru.job4j.ood.lsp.example;

public class Baby {
    private int ageMonths;
    private String familyNumber;

    public String getFamilyNumber() {
        return familyNumber;
    }

    public void setFamilyNumber(String familyNumber) {
        validate(familyNumber);
        this.familyNumber = familyNumber;
    }

    private void validate(String familyNumber) {
    }

    public Baby(int ageMonths) {
        this.ageMonths = ageMonths;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public Sound speak(int age) {
        return null;
    }

    public Document showDocument(String years) {
        Document rsl = null;
        if (Integer.parseInt(years) >= 14) {
            rsl = new Document("Pasport");
        } else {
            rsl = new Document("Svidetelstvo o rozhdenii");
        }
        return rsl;
    }

}
