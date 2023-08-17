package ru.job4j.ood.isp.badisp;

public class ToBeDel {
    public static void main(String[] args) {
        CarR carR = new CarR();
        Boat boat = new Boat();
        Amfibia amfibia = new Amfibia();
        drive(carR);
        flow(boat);
        drive(amfibia);
        flow(amfibia);
    }

    static void drive(RoadTransport roadTransport) {
        roadTransport.drive();
    }

    static void flow(WaterTransport waterTransport) {
        waterTransport.flow();
    }

    static class CarR implements RoadTransport {
        @Override
        public void drive() {
            System.out.println("Many code: car drive");
        }
    }

    static class Boat implements WaterTransport {
        @Override
        public void flow() {
            System.out.println("Many code: boat flow");
        }
    }

    static class Amfibia implements WaterTransport, RoadTransport {
        @Override
        public void drive() {
            System.out.println("Many code: Amfibia drives wore than real car");
        }

        @Override
        public void flow() {
            System.out.println("Many code: Amfibia flows wore than real boat");
        }
    }
}