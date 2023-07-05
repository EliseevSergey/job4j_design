package ru.job4j.ood.lsp.example;

public class BabySitting {
    Baby bigger = new BiggerSister(13);
    Baby smaller = new LittleSister(6);

    public void reply(Baby baby) {
        bigger.speak(baby.getAgeMonths());
        smaller.speak(baby.getAgeMonths());
    }

    public void checkDocuments(Baby baby) {
        /*if (baby.showDocument.equals("Svidetelstvo o rozhdenii") || equals("Svidetelstvo o rozhdenii") ) {
            System.out.println("ok GO");
        } if (baby.showDocument.equals("")) {
            System.out.println("ok GO");
        }*/
    }
}
