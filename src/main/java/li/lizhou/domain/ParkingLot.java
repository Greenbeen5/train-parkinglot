package li.lizhou.domain;

import li.lizhou.exception.NotEnoughParkingSpaceException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static final int DEFAULT_CAPACITY = 100;

    private final Map<Ticket, Car> parkingSpace;
    private final Integer id;
    private final int capacity;

    public Ticket park(Car car) {
        if (getSize() < capacity) {
            Ticket ticket = new Ticket();
            parkingSpace.put(ticket, car);
            return ticket;
        } else {
            throw new NotEnoughParkingSpaceException("Not enough parking space");
        }
    }

    public Car getCar(Ticket ticket) {
        Car car = parkingSpace.get(ticket);
        parkingSpace.remove(ticket);
        return car;
    }

    public ParkingLot(int id) {
        this(id, DEFAULT_CAPACITY);
    }

    public ParkingLot(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.parkingSpace = new HashMap<>(capacity);
    }

    public boolean isFull() {
        return getSize() == capacity;
    }

    public Integer getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return parkingSpace.size();
    }
}
