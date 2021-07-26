package li.lizhou.domain;

import li.lizhou.exception.CarNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private final Map<Ticket, Car> carMap;

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        carMap.put(ticket, car);
        return ticket;
    }

    public Car getCar(Ticket ticket) {
        Car car = carMap.get(ticket);
        if (null == car) {
            throw new CarNotFoundException("There is no car with ticket id: " + ticket.getId());
        }
        carMap.remove(ticket);
        return car;
    }

    public ParkingLot() {
        carMap = new HashMap<>();
    }
}
