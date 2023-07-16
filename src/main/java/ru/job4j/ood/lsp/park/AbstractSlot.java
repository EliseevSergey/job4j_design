package ru.job4j.ood.lsp.park;

public abstract class AbstractSlot implements Slot {
    protected boolean truckType;
    protected boolean freeStatus;
    protected Vehicle content;

    @Override
    public boolean getTruckType() {
        return truckType;
    }

    @Override
    public boolean getFreeStatus() {
        return freeStatus;
    }

    @Override
    public Vehicle getContent() {
        return content;
    }

    @Override
    public void setContent(Vehicle content) {
        this.content = content;
    }

    @Override
    public void setFreeStatus(boolean freeStatus) {
    }

    @Override
    public boolean acceptVehicle(Vehicle vehicle) {
        boolean rsl = vehicle.getSize() == 1 && !truckType;
        if (vehicle.getSize() == 1 && truckType) {
            rsl = false;
            }
        if (vehicle.getSize() > 1 && !truckType) {
            rsl = false;
        }
        if (vehicle.getSize() > 1 && truckType) {
            rsl = true;
        }
        return rsl;
    }
}
