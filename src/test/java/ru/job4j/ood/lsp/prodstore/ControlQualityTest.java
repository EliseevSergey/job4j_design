package ru.job4j.ood.lsp.prodstore;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {
    private Warehouse wh = new Warehouse();
    private Shop sh = new Shop();
    private Trash tr = new Trash();
    private List<Store> storages = List.of(wh, sh, tr);
    private ControlQuality qc = new ControlQuality(storages);

    @Test
    void distributeToWarehouse() {
        Calendar expMilk = new GregorianCalendar(2023, 7, 15);
        Calendar crtMilk = new GregorianCalendar(2023, 6, 01);
        Food milkToWarehouse = new Food("MilkToWh", crtMilk, expMilk, 100D, 30);
        qc.distribute(milkToWarehouse);
        Food milkExpected = wh.findBy(f -> f.equals(milkToWarehouse)).get(0);
        assertEquals(milkExpected, milkToWarehouse);
    }

    @Test
    void distributeToShop() {
        Calendar expPotato = new GregorianCalendar(2023, 11, 1);
        Calendar crtPotato = new GregorianCalendar(2023, 4, 1);
        Food potatoNoDiscount = new Food("PotatoNoDiscount", crtPotato, expPotato, 100D, 30);
        qc.distribute(potatoNoDiscount);
        Food potatoExpectedNoDiscount = sh.findBy(f -> f.equals(potatoNoDiscount)).get(0);
        assertEquals(potatoExpectedNoDiscount, potatoNoDiscount);
    }

    @Test
    void distributeToTrash() {
        Calendar expTrash = new GregorianCalendar(2023, 6, 6);
        Calendar crtTrash = new GregorianCalendar(2023, 5, 5);
        Food meatToTrash = new Food("MeatToTrash", crtTrash, expTrash, 100D, 30);
        qc.distribute(meatToTrash);
        Food trashExpected = tr.findBy(f -> f.equals(meatToTrash)).get(0);
        assertEquals(trashExpected, meatToTrash);
    }

    @Test
    void checkPriceDown() {
        Calendar expFish = new GregorianCalendar(2023, 6, 10);
        Calendar crtFish = new GregorianCalendar(2023, 6, 6);
        Food fishToShopDiscount = new Food("FishToDiscount", crtFish, expFish, 100D, 30);
        qc.distribute(fishToShopDiscount);
        double priceDown = sh.findBy(f -> f.equals(fishToShopDiscount)).get(0).getPrice();
        assertEquals(70D, priceDown);
    }
}