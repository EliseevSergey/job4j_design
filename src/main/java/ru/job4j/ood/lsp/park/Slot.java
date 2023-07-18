package ru.job4j.ood.lsp.park;

public interface Slot {
    boolean getTruckType();

    void setTruckType(boolean truckType);

    boolean acceptVehicle(Vehicle vehicle);

    Vehicle getContent();

    void setContent(Vehicle vehicle);
}
