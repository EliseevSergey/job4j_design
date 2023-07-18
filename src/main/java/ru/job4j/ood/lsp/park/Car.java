package ru.job4j.ood.lsp.park;

public class Car extends AbstractVehicle {
    public Car(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Car{" +
                "size=" + size +
                ", parkStatus=" + parkStatus +
                ", num='" + num + '\'' +
                '}';
    }
}
