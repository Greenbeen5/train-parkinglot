package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.enums.ParkingStrategyEnum;
import li.lizhou.exception.NotEnoughParkingSpaceException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends AbstractParkingBoy {

    static {
        parkingStrategy = ParkingStrategyEnum.SMART;
    }

    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) {
        return parkingLots
                .stream()
                .max(Comparator.comparingInt((p) -> p.getCapacity() - p.getSize()))
                .orElseThrow(() -> new NotEnoughParkingSpaceException("All the parking lots are full"))
                .park(car);

    }

}
