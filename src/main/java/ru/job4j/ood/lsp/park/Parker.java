package ru.job4j.ood.lsp.park;

import java.util.List;

public class Parker {
    private Parking parking;

    public Parker(Parking parking) {
        this.parking = parking;
    }

    public boolean park(List<Vehicle> in) {
        boolean rsl = false;
        for (Vehicle vh : in) {
            if (vh.getSize() == 1) {
                rsl = parkCar(vh);
            } else {
                if (parking.freeTruckSlotQty > 0) {
                    rsl = parkTruckToTruckSlot(vh);
                } else {
                    rsl = parkTruckToCarsSlots(vh);
                }
            }
        }
        return rsl;
    }

    private boolean parkCar(Vehicle car) {
        boolean rsl = false;
        for (int i = parking.truckCapacity; i < parking.getSlots().size(); i++) {
            if (parking.slots.get(i).acceptVehicle(car)) {
                parking.slots.get(i).setContent(car);
                rsl = true;
                parking.freeCarSlotQty--;
                car.setParkStatus(true);
                break;
            }
        }
            if (!rsl) {
                throw new IllegalStateException("No space for car");
            }
            return rsl;
        }

    private boolean parkTruckToTruckSlot(Vehicle vhcl) {
        boolean rsl = false;
        if (parking.freeTruckSlotQty > 0) {
            for (int i = 0; i < parking.truckCapacity; i++) {
                if (parking.slots.get(i).acceptVehicle(vhcl)) {
                    parking.slots.get(i).setContent(vhcl);
                    rsl = true;
                    parking.freeTruckSlotQty--;
                    vhcl.setParkStatus(true);
                    break;
                }
            }
        }
        return rsl;
    }

    private boolean parkTruckToCarsSlots(Vehicle vhcl) {
            boolean rsl = false;
            int requiredSpace = vhcl.getSize();
            int available = 0;
            int indexEndToPark = -1;
                for (int i = parking.truckCapacity; i < parking.getSlots().size(); i++) {
                    if (parking.slots.get(i).getContent() == null) {
                        indexEndToPark = i;
                        available++;
                        if (available == requiredSpace) {
                            break;
                        }
                    } else {
                        available = 0;
                        indexEndToPark = -1;
                    }
                }
            if (available == requiredSpace) {
                for (int i = indexEndToPark - requiredSpace + 1; i < indexEndToPark + 1; i++) {
                    parking.getSlots().get(i).setContent(vhcl);
                    parking.freeCarSlotQty--;
                }
                vhcl.setParkStatus(true);
                rsl = true;
            }
            if (!rsl) {
                throw new IllegalStateException("No space for truck even on car's slots.");
            }
            return rsl;
    }

    public boolean unPark(List<Vehicle> out) {
        boolean rsl = false;
        for (Vehicle vh : out) {
            List<Integer> indexToRelease = parking.indexOfVehicle(vh);
            vh.setParkStatus(false);
            for (Integer i : indexToRelease) {
                parking.slots.get(i).setContent(null);
                rsl = true;
                if (vh.getSize() == 1) {
                    parking.freeCarSlotQty++;
                } else {
                    if (!parking.slots.get(i).getTruckType()) {
                        parking.freeCarSlotQty++;
                    } else {
                        parking.freeTruckSlotQty++;
                    }
                }
            }
        }
        return rsl;
    }
}
