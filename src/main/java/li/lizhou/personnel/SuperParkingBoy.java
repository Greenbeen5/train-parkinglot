package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends AbstractParkingBoy {

    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots
                .stream()
                .min(Comparator.comparingDouble((p) -> (double) p.getSize() / (double) p.getCapacity()))
                .orElseThrow(() -> new NotEnoughParkingSpaceException("All the parking lots are full"))
                .park(car);

    }
}
