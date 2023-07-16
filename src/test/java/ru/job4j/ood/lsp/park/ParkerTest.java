package ru.job4j.ood.lsp.park;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ParkerTest {
    private List<Vehicle> in = List.of(new Car(), new Car(), new Truck(2), new Truck(3));
    private Parking parking = new Parking(2, 2);
    private Parker parker = new Parker(parking);

    @Test
    void whenInThenVehicleParkStatusTrue() {
        assertEquals(true, parker.park(in));
        assertEquals(true, in.get(0).getParkStatus());
        assertEquals(true, in.get(3).getParkStatus());
    }

    @Test
    void whenInCheckForLeftSpace() {
        assertEquals(true, parker.park(in));
        assertEquals(0, parking.qtyFreeCarSlot());
        assertEquals(0, parking.qtyFreeTruckSlot());
    }

    @Test
    void whenNoSpaceThenThrowEx() {
        Car noSpaceForThis = new Car();
        in.add(noSpaceForThis);
        assertThatThrownBy(() -> parker.park(in))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not enough space for parking");
    }

    @Test
    void whenTruckSpaceIsFullThenTakeCarSpaceAndCheckLeftSpaceAfter() {
        Parking park = new Parking(5, 1);
        Parker parker = new Parker(park);
        Car car = new Car();
        Truck truck = new Truck(2);
        Truck truckForCarSpace = new Truck(5);
        List<Vehicle> input = List.of(car, truck, truckForCarSpace);
        assertEquals(true, parker.park(input));
        assertEquals(1, park.qtyFreeCarSlot());
        assertEquals(0, park.qtyFreeTruckSlot());
    }

    @Test
    void unparkTruckAndCarCheckFreeSlot() {
        parker.park(in);
        Truck truckLeave = new Truck(2);
        Car carLeave = new Car();
        List<Vehicle> output = List.of(truckLeave, carLeave);
        assertEquals(true, parker.unPark(output));
        assertEquals(1, parking.qtyFreeCarSlot());
        assertEquals(1, parking.qtyFreeTruckSlot());
    }

    @Test
    void unparkTruckFromCarsSpacesThenSlotsGetEmptyForCar() {
        Parking park = new Parking(5, 1);
        Parker parker = new Parker(park);
        Car car = new Car();
        Truck truck = new Truck(2);
        Truck truckForCarSpace = new Truck(4);
        List<Vehicle> input = List.of(car, truck, truckForCarSpace);
        parker.park(in);

        assertEquals(0, park.qtyFreeTruckSlot());
        assertEquals(0, park.qtyFreeCarSlot());

        List<Vehicle> output = List.of(truckForCarSpace);

        assertEquals(true, parker.unPark(output));
        assertEquals(4, park.qtyFreeCarSlot());
        assertEquals(0, park.qtyFreeTruckSlot());
    }
}