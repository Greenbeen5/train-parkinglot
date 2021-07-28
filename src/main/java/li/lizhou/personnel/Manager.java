package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class Manager {

    private final List<ParkingLot> parkingLots;

    public Ticket park(Car car, ParkingBoy parkingBoy){
        return parkingBoy.park(car, parkingLots);
    }

    public Ticket park(Car car){
        // The manager will try to park the car
        // at the biggest parking lot
        // (in other words, the first possible
        // parking lot ordered by capacity descending).
        return parkingLots.stream()
                .filter(Predicate.not(ParkingLot::isFull))
                .max(Comparator.comparingInt(ParkingLot::getCapacity))
                .orElseThrow(() -> new NotEnoughParkingSpaceException("All the parking lots are full"))
                .park(car);
    }

    public ParkingLot getParkingLot(int id){
        return parkingLots.stream()
                .filter((p) -> p.getId().equals(id))
                .findAny()
                .orElseThrow();
    }

    public Manager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
}
