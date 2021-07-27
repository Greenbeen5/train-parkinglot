package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

import java.util.ArrayList;
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
        ParkingLot chosenParkingLot = parkingLots
                .stream()
                .filter(Predicate.not(ParkingLot::isFull))
                .max(Comparator.comparingInt(ParkingLot::getCapacity))
                .orElse(null);
        if(null == chosenParkingLot) {
            return null;
        } else {
            return chosenParkingLot.park(car);
        }
    }

    public ParkingLot getParkingLot(int id){
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getId().equals(id)) {
                return parkingLot;
            }
        }
        return null;
    }

    public Manager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
}
