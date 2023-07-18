package ru.job4j.ood.lsp.park;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    int carCapacity;
    int truckCapacity;
    int freeCarSlotQty;
    int freeTruckSlotQty;
    List<Slot> slots = new ArrayList();

    public Parking(int carCapacity, int truckCapacity) {
        this.carCapacity = carCapacity;
        this.truckCapacity = truckCapacity;

        for (int i = 0; i < truckCapacity; i++) {
            slots.add(new TruckSlot());
        }
        for (int i = truckCapacity; i < truckCapacity + carCapacity; i++) {
            slots.add(new CarSlot());
        }
        this.freeTruckSlotQty = truckCapacity;
        this.freeCarSlotQty = carCapacity;
    }

    public int getFreeCarSlotQty() {
        return freeCarSlotQty;
    }

    public int getFreeTruckSlotQty() {
        return freeTruckSlotQty;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public int getTruckCapacity() {
        return truckCapacity;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public List<Integer> indexOfVehicle(Vehicle vehicle) {
        List<Integer> rsl = new ArrayList<>();
        int index = -1;
        for (Slot slot : slots) {
            index++;
            if (slot.getContent() != null) {
                Vehicle inside = slot.getContent();
                if (inside.equals(vehicle)) {
                    rsl.add(index);
                    if (!slot.getTruckType() && inside.getSize() > 1) {
                        for (int i = 1; i < vehicle.getSize(); i++) {
                            rsl.add(index + i);
                        }
                        break;
                    }
                }
            }
        }
        return rsl;
    }
}
