package ru.job4j.ood.isp.menu;

public class ActionDefault implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("-=Some action=-");
    }
}
