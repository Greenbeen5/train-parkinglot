package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

import java.util.Comparator;
import java.util.Optional;

public class SuperParkingBoy extends AbstractParkingBoy {
    @Override
    public Ticket park(Car car) {
        Optional<ParkingLot> parkingLotWithLowestOccupancyRateOptional = parkingLots
                .stream()
                .min(Comparator.comparingDouble((p) -> (double) p.getSize() / (double) p.getCapacity()));
        return parkingLotWithLowestOccupancyRateOptional
                .map(parkingLot -> parkingLot.park(car))
                .orElse(null);
    }
}
