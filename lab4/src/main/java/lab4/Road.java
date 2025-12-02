package lab4;

import lab4.vehicle.Vehicle;
import lab4.human.Human;

import java.util.ArrayList;
import java.util.List;

public class Road {

    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<? extends Human> vehicle) {
        carsInRoad.add(vehicle);
    }

    public int getCountOfHumans() {
        int total = 0;

        for (Vehicle<? extends Human> v : carsInRoad) {
            total += v.getPassengers().size();
        }

        return total;
    }
}
