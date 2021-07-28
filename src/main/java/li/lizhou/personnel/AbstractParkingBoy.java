package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

import java.util.List;
import java.util.Objects;

public abstract class AbstractParkingBoy implements ParkingBoy {

    public int countCars(List<ParkingLot> parkingLots){
        return parkingLots.stream()
                .mapToInt(ParkingLot::getSize)
                .sum();
    }

    public Car getCar(Ticket ticket, List<ParkingLot> parkingLots) {
        return parkingLots
                .stream()
                .map(parkingLot -> parkingLot.getCar(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow();
    }

    abstract public Ticket park(Car car, List<ParkingLot> parkingLots);

}
