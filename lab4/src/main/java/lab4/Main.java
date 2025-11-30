package lab4;

import lab4.human.*;
import lab4.vehicle.*;

public class Main {
    public static void main(String[] args) {

        Bus bus = new Bus(3);
        Taxi taxi = new Taxi(2);
        FireTruck ft = new FireTruck(2);
        PoliceCar pc = new PoliceCar(2);

        bus.boardPassenger(new Passenger("Artem"));
        bus.boardPassenger(new Policeman("Serhiy"));
        bus.boardPassenger(new Firefighter("Ihor"));

        taxi.boardPassenger(new Passenger("Olena"));

        ft.boardPassenger(new Firefighter("Maryna"));

        pc.boardPassenger(new Policeman("Dmytro"));

        Road r = new Road();
        r.addCarToRoad(bus);
        r.addCarToRoad(taxi);
        r.addCarToRoad(ft);
        r.addCarToRoad(pc);

        System.out.println("Amount of people on the road: " + r.getCountOfHumans());
    }
}
