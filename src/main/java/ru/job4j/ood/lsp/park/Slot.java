package ru.job4j.ood.lsp.park;

public interface Slot {
    boolean getFreeStatus();

    void setFreeStatus(boolean freeStatus);

    boolean getTruckType();

    boolean acceptVehicle(Vehicle vehicle);

    Vehicle getContent();

    void setContent(Vehicle vehicle);
}
