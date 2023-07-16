package ru.job4j.ood.lsp.park;

import java.util.List;

public class Parker {
    Parking parking;

    public Parker(Parking parking) {
        this.parking = parking;
    }

    public boolean park(List<Vehicle> in) {
      return false;
    }

    public boolean unPark(List<Vehicle> out) {
        return false;
    }
}
