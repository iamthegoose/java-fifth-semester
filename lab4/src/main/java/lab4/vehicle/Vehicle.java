package lab4.vehicle;

import lab4.human.Human;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {

    protected final int maxSeats;
    protected final List<T> passengers = new ArrayList<>();

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void boardPassenger(T passenger) {
        if (passengers.size() >= maxSeats)
            throw new RuntimeException("Місць більше немає!");

        passengers.add(passenger);
    }

    public void dropPassenger(T passenger) {
        if (!passengers.remove(passenger))
            throw new RuntimeException("Цей пасажир тут не сидить!");
    }

    public List<T> getPassengers() {
        return passengers;
    }
}
