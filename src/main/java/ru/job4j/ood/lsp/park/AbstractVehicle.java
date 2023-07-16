package ru.job4j.ood.lsp.park;

public abstract class AbstractVehicle implements Vehicle {
    protected int size = 1;
    protected boolean parkStatus = false;

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean getParkStatus() {
        return parkStatus;
    }

    @Override
    public void setParkStatus(boolean parkStatus) {
        this.parkStatus = parkStatus;
    }
}
