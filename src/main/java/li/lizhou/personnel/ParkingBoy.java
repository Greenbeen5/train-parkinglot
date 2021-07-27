package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

import java.util.List;

public interface ParkingBoy {

    int countCars(List<ParkingLot> parkingLots);

    Car getCar(Ticket ticket, List<ParkingLot> parkingLots);

    Ticket park(Car car, List<ParkingLot> parkingLots);
}
