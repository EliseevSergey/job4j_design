package ru.job4j.ood.lsp.prodstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {
    private List<Store> storages = List.of(new Warehouse(), new Shop(), new Trash());
    private ControlQuality qc = new ControlQuality(storages);
    private List<Food> goods;
    Calendar baseDate = new GregorianCalendar(2023, 6, 11);

    @BeforeEach
    void distribute() {
        Calendar expMilk = new GregorianCalendar(2023, 7, 15);
        Calendar crtMilk = new GregorianCalendar(2023, 6, 01);
        Food milkToWarehouse = new Food("MilkToWh", crtMilk, expMilk, 100D, 30);

        Calendar expPotato = new GregorianCalendar(2023, 11, 1);
        Calendar crtPotato = new GregorianCalendar(2023, 4, 1);
        Food potatoNoDiscount = new Food("PotatoNoDiscount", crtPotato, expPotato, 100D, 30);

        Calendar expTrash = new GregorianCalendar(2023, 6, 6);
        Calendar crtTrash = new GregorianCalendar(2023, 5, 5);
        Food meatToTrash = new Food("MeatToTrash", crtTrash, expTrash, 100D, 30);

        Calendar expFish = new GregorianCalendar(2023, 6, 12);
        Calendar crtFish = new GregorianCalendar(2023, 6, 6);
        Food fishToShopDiscount = new Food("FishToDiscount", crtFish, expFish, 100D, 30);

        goods = List.of(milkToWarehouse, potatoNoDiscount, meatToTrash, fishToShopDiscount);
        qc.distribute(goods, baseDate);
        System.out.println("good size " + goods.size());
        System.out.println("storages size " + storages.size());
        System.out.println();
        System.out.println("Ниже лист товаров на складе");
        storages.get(0)
                .findBy(t -> true)
                .stream()
                .forEach(f -> System.out.println(f.getName()));
        System.out.println();

        System.out.println("Ниже лист товаров в магазине");
        storages.get(1)
                .findBy(t -> true)
                .stream()
                .forEach(f -> System.out.println(f.getName()));
        System.out.println();
        System.out.println("Ниже лист товаров в мусорке");
        storages.get(2)
                .findBy(t -> true)
                .stream()
                .forEach(f -> System.out.println(f.getName()));
        System.out.println();


        System.out.println("Ниже склады");
        storages.stream()
                .forEach(f -> System.out.println(f.getClass()));
    }

    @Test
    void distributeToWarehouse() {
        Food milkRsl = storages.get(0).
                findBy(f -> f.getName().equals("MilkToWh")).get(0);
        Food milkExpected = goods.get(0);
        assertEquals(milkExpected, milkRsl);
    }

    @Test
    void distributeToShop() {
        Food potatoNoDiscountRsl = storages.get(1).
                findBy(f -> f.getName().equals("PotatoNoDiscount")).get(0);
        Food potatoNoDiscountExpected = goods.get(1);
        assertEquals(potatoNoDiscountExpected, potatoNoDiscountRsl);
    }

    @Test
    void distributeToTrash() {
        Food meatToTrashRsl = storages.get(2).
                findBy(f -> f.getName().equals("MeatToTrash")).get(0);
        Food meatToTrashExpected = goods.get(2);
        assertEquals(meatToTrashExpected, meatToTrashRsl);
    }

    @Test
    void checkPriceDown() {
        double priceDownRsl = storages.get(1).
                findBy(f -> f.getName().equals("FishToDiscount")).get(0).getPrice();
        Food fishToShopDiscountExpected = goods.get(0);
        assertEquals(70D, priceDownRsl);
    }
}