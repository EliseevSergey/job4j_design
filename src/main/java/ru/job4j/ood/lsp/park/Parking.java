package ru.job4j.ood.lsp.park;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    int carSlotCapacity;
    int truckSlotCapacity;
    List<Slot> slots = new ArrayList();

    public Parking(int carSlotCapacity, int truckSlotCapacity) {
        this.carSlotCapacity = carSlotCapacity;
        this.truckSlotCapacity = truckSlotCapacity;
    }

    public int qtyFreeCarSlot() {
        return -1;
    }

    public int qtyFreeTruckSlot() {
        return -1;
    }
}
