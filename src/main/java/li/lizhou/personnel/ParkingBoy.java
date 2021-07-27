package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

public interface ParkingBoy {

    void addParkingLot(ParkingLot parkingLot);

    int countCars();

    ParkingLot getParkingLot(int parkingLotId);

    Car getCar(Ticket ticket);

    Ticket park(Car car);
}
