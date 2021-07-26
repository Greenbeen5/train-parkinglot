package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;

import java.util.ArrayList;
import java.util.List;

public class GraduateParkingBoy implements ParkingBoy {

    private final List<ParkingLot> parkingLots;

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    @Override
    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()){
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughParkingSpaceException("All the parking lots are full");
    }

    @Override
    public int countCars() {
        return parkingLots.stream()
                .mapToInt(ParkingLot::getSize)
                .sum();
    }

    @Override
    public ParkingLot getParkingLot(int parkingLotId) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getId().equals(parkingLotId)) {
                return parkingLot;
            }
        }
        return null;
    }

    @Override
    public Car getCar(Ticket ticket) {
        Car car = null;
        for (ParkingLot parkingLot : parkingLots) {
            car = parkingLot.getCar(ticket);
        }
        return car;
    }

    public GraduateParkingBoy() {
        parkingLots = new ArrayList<>();
    }
}
