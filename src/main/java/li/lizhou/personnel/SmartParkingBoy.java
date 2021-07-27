package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

import java.util.Comparator;
import java.util.Optional;


public class SmartParkingBoy extends AbstractParkingBoy {

    @Override
    public Ticket park(Car car) {
        Optional<ParkingLot> parkingLotWithMostSpaceOptional = parkingLots
                .stream()
                .max(Comparator.comparingInt((p) -> p.getCapacity() - p.getSize()));
        return parkingLotWithMostSpaceOptional
                .map(parkingLot -> parkingLot.park(car))
                .orElse(null);
    }

}
