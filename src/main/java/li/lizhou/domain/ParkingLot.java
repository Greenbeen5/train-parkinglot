package li.lizhou.domain;

import li.lizhou.exception.NotEnoughParkingSpaceException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static final int DEFAULT_CAPACITY = 100;

    private final Map<Ticket, Car> parkingSpace;
    private Integer id;
    private int capacity;

    private int size;

    public Ticket park(Car car) {
        if (size < capacity) {
            Ticket ticket = new Ticket();
            parkingSpace.put(ticket, car);
            size++;
            return ticket;
        } else {
            throw new NotEnoughParkingSpaceException("Not enough parking space");
        }
    }

    public Car getCar(Ticket ticket) {
        Car car = parkingSpace.get(ticket);
        parkingSpace.remove(ticket);
        size--;
        return car;
    }

    public ParkingLot(int id) {
        this(id, DEFAULT_CAPACITY);
    }

    public ParkingLot(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.size = 0;
        this.parkingSpace = new HashMap<>(capacity);
    }

    public boolean isFull() {
        return size == capacity;
    }

    public Integer getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }
}
