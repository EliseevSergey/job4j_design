package ru.job4j.ood.lsp.park;

public class Truck extends AbstractVehicle {
    public Truck(int size, String num) {
        this.size = size;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Truck{" + "size=" + size
                + ", parkStatus=" + parkStatus
                + ", num='" + num + '\'' + '}';
    }
}
