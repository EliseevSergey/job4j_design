package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSameAndPrinterCheck() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.add("Купить хлеб", "Купить черный", STUB_ACTION);
        menu.add("Купить хлеб", "Купить белый", STUB_ACTION);

        assertEquals(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."),
                menu.select("Сходить в магазин").get());
        assertEquals(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."),
                menu.select("Купить продукты").get());
        assertEquals(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."),
                menu.select("Покормить собаку").get());
        assertEquals(new Menu.MenuItemInfo(
                        "Купить черный", List.of(), STUB_ACTION, "1.1.1.1."),
                menu.select("Купить черный").get());

        assertEquals(new Menu.MenuItemInfo(
                        "Купить белый", List.of(), STUB_ACTION, "1.1.1.2."),
                menu.select("Купить белый").get());

        String expected = new StringBuilder().append("  1. Сходить в магазин")
                .append(System.lineSeparator())
                .append("    1.1. Купить продукты")
                .append(System.lineSeparator())
                .append("      1.1.1. Купить хлеб")
                .append(System.lineSeparator())
                .append("        1.1.1.1. Купить черный")
                .append(System.lineSeparator())
                .append("        1.1.1.2. Купить белый")
                .append(System.lineSeparator())
                .append("      1.1.2. Купить молоко")
                .append(System.lineSeparator())
                .append("  2. Покормить собаку")
                .append(System.lineSeparator())
                .toString();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        MenuPrinter menuPrinter = new Printer();
        menuPrinter.print(menu);
        System.setOut(System.out);
        assertEquals(expected, output.toString());
    }
}