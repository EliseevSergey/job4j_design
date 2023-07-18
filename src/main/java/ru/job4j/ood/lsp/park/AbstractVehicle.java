package ru.job4j.ood.lsp.park;

import java.util.Objects;

public abstract class AbstractVehicle implements Vehicle {
    protected int size = 1;
    protected boolean parkStatus = false;
    protected String num;

    @Override
    public String getNum() {
        return this.num;
    }

    @Override
    public void setNum(String num) {
        this.num = num;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractVehicle that = (AbstractVehicle) o;
        return size == that.size && Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, num);
    }
}
