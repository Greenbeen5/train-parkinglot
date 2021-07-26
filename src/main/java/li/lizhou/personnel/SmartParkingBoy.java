package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

public class SmartParkingBoy implements ParkingBoy {

    @Override
    public void addParkingLot(ParkingLot parkingLot) {

    }

    @Override
    public Ticket park(Car car) {
        return null;
    }

    @Override
    public int countCars() {
        return 0;
    }

    @Override
    public ParkingLot getParkingLot(int parkingLotId) {
        return null;
    }

    @Override
    public Car getCar(Ticket ticket) {
        return null;
    }
}
