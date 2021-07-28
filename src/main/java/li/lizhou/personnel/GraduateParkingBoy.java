package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;

import java.util.List;
import java.util.function.Predicate;

public class GraduateParkingBoy extends AbstractParkingBoy {

    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(Predicate.not(ParkingLot::isFull))
                .findFirst()
                .orElseThrow(() -> new NotEnoughParkingSpaceException("All the parking lots are full"))
                .park(car);
    }

}
