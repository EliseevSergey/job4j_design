package ru.job4j.ood.lsp.park;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ParkerTest {

    private Parking parking = new Parking(2, 2);
    private Parker parker = new Parker(parking);
    private Car firstCar = new Car("FC");
    private Car secondCar = new Car("SC");
    private Car thirdCar = new Car("TC");
    private Truck firstTruck = new Truck(2, "T1");
    private Truck secondTruck = new Truck(3, "T2");
    private List<Vehicle> in = List.of(firstCar, secondCar, firstTruck, secondTruck);

    @Test
    void whenInCheckContentAndStatus() {
        assertEquals(true, parker.park(in));
        assertEquals(firstTruck, parking.getSlots().get(0).getContent());
        assertEquals(secondTruck, parking.getSlots().get(1).getContent());
        assertEquals(firstCar, parking.getSlots().get(2).getContent());
        assertEquals(secondCar, parking.getSlots().get(3).getContent());
        assertEquals(true, in.get(0).getParkStatus());
        assertEquals(true, in.get(3).getParkStatus());
    }

    @Test
    void whenUnparkAllCheckContentAndStatus() {
        parker.park(in);
        /*for (Slot sl : parking.getSlots()) {
            System.out.printf("%s.  TruckSlotType is %s %n", sl.getContent(), sl.getTruckType());
        }*/
        assertEquals(true, parker.unPark(in));
        /*for (Slot sl : parking.getSlots()) {
            System.out.printf("%s.  TruckSlotType is %s %n", sl.getContent(), sl.getTruckType());
        }*/
        assertEquals(null, parking.getSlots().get(0).getContent());
        assertEquals(null, parking.getSlots().get(1).getContent());
        assertEquals(null, parking.getSlots().get(2).getContent());
        assertEquals(null, parking.getSlots().get(3).getContent());

        assertEquals(false, in.get(0).getParkStatus());
        assertEquals(false, in.get(3).getParkStatus());
        assertEquals(2, parking.getFreeCarSlotQty());
        assertEquals(2, parking.getFreeTruckSlotQty());
    }

    @Test
    void findIndex() {
        parker.park(in);
        for (Slot sl : parking.getSlots()) {
            System.out.printf("%s.  TruckSlotType is %s %n", sl.getContent(), sl.getTruckType());
        }
        assertEquals(3, parking.indexOfVehicle(secondCar).get(0));
        assertEquals(2, parking.indexOfVehicle(firstCar).get(0));
        assertEquals(1, parking.indexOfVehicle(secondTruck).get(0));
        assertEquals(0, parking.indexOfVehicle(firstTruck).get(0));
    }

    @Test
    void findTruckIndexOnCarSlots() {
        Parking park = new Parking(7, 1);
        Parker parker = new Parker(park);
        Car tailCar = new Car("TC");
        Truck truckForCarSlots = new Truck(5, "TFCS");
        List<Vehicle> input = List.of(firstCar, firstTruck, truckForCarSlots, tailCar);
        assertEquals(true, parker.park(input));
        List<Integer> expectedForTruckOnCarSlots = List.of(2, 3, 4, 5, 6);
        List<Integer> expectedForTailCar = List.of(7);
        /*for (Slot sl : park.getSlots()) {
            System.out.printf("%s.  TruckSlotType is %s %n", sl.getContent(), sl.getTruckType());
        }*/
        assertEquals(expectedForTruckOnCarSlots, park.indexOfVehicle(truckForCarSlots));
        assertEquals(expectedForTailCar, park.indexOfVehicle(tailCar));

    }

    @Test
    void whenInCheckForLeftSpace() {
        parker.park(in);
        assertEquals(secondCar, parking.getSlots().get(3).getContent());
        assertEquals(0, parking.getFreeCarSlotQty());
        assertEquals(0, parking.getFreeTruckSlotQty());
    }

    @Test
    void whenNoSpaceThenThrowEx() {
        Car noSpaceForThis = new Car("NSFT");
        in = List.of(firstCar, secondCar, firstTruck, secondTruck, noSpaceForThis);
        assertThatThrownBy(() -> parker.park(in))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No space for car");
    }

    @Test
    void whenTruckSpaceIsFullThenTakeCarSpaceAndCheckLeftSpaceAfter() {
        Parking park = new Parking(10, 1);
        Parker parker = new Parker(park);
        Truck truckForCarSpace = new Truck(2, "TFCS");
        Truck truckForCarSpaceTail = new Truck(3, "TFCST");
        List<Vehicle> input =
                List.of(firstCar, firstTruck, secondCar, truckForCarSpace, thirdCar, truckForCarSpaceTail);
        assertEquals(true, parker.park(input));
        for (Slot sl : park.getSlots()) {
            System.out.printf("%s.  TruckSlotType is %s %n", sl.getContent(), sl.getTruckType());
        }
        assertEquals(2, park.getFreeCarSlotQty());
        assertEquals(0, park.getFreeTruckSlotQty());
        assertEquals(firstTruck, park.getSlots().get(0).getContent());
        assertEquals(firstCar, park.getSlots().get(1).getContent());
        assertEquals(secondCar, park.getSlots().get(2).getContent());
        assertEquals(truckForCarSpace, park.getSlots().get(3).getContent());
        assertEquals(truckForCarSpace, park.getSlots().get(4).getContent());
        assertEquals(thirdCar, park.getSlots().get(5).getContent());
        assertEquals(truckForCarSpaceTail, park.getSlots().get(6).getContent());
        assertEquals(truckForCarSpaceTail, park.getSlots().get(7).getContent());
        assertEquals(truckForCarSpaceTail, park.getSlots().get(8).getContent());
        assertEquals(null, park.getSlots().get(9).getContent());
        assertEquals(null, park.getSlots().get(10).getContent());
        List<Vehicle> out = List.of(truckForCarSpace);
        System.out.println(parking.freeTruckSlotQty);
        System.out.println(parking.freeCarSlotQty);
        parker.unPark(out);
        System.out.println("AFTER");
        for (Slot sl : park.getSlots()) {
            System.out.printf("%s.  TruckSlotType is %s %n", sl.getContent(), sl.getTruckType());
        }
        System.out.println(parking.freeTruckSlotQty);
        System.out.println(parking.freeCarSlotQty);
    }

    @Test
    void whenTruckSpaceIsFullThenTakeCarSpaceButNotEnoughSpace() {
        Parking park = new Parking(5, 1);
        Parker parker = new Parker(park);
        Truck truckForCarSpace = new Truck(5, "TFCS");
        List<Vehicle> input = List.of(firstCar, firstTruck, truckForCarSpace);
        assertThatThrownBy(() -> parker.park(input))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No space for truck even on car's slots.");
    }
}