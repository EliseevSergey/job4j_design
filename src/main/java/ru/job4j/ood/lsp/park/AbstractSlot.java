package ru.job4j.ood.lsp.park;

public abstract class AbstractSlot implements Slot {
    protected boolean truckType;
    protected Vehicle content;

    @Override
    public boolean getTruckType() {
        return truckType;
    }

    @Override
    public void setTruckType(boolean truckType) {
        this.truckType = truckType;
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
    public boolean acceptVehicle(Vehicle vehicle) {
        boolean rsl = false;
        if (content == null) {
            if (vehicle.getSize() == 1 && !truckType) {
                rsl = true;
            }
            if (vehicle.getSize() == 1 && truckType) {
                rsl = false;
            }
            if (vehicle.getSize() > 1 && !truckType) {
                rsl = false;
            }
            if (vehicle.getSize() > 1 && truckType) {
                rsl = true;
            }
        }
        return rsl;
    }
}
