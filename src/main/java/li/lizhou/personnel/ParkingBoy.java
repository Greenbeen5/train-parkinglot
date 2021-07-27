package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy {

    protected final List<ParkingLot> parkingLots;

    public ParkingBoy(){
        parkingLots = new ArrayList<>();
    }

    void addParkingLot(ParkingLot parkingLot){
        parkingLots.add(parkingLot);
    }

    int countCars(){
        return parkingLots.stream()
                .mapToInt(ParkingLot::getSize)
                .sum();
    }

    ParkingLot getParkingLot(int parkingLotId) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getId().equals(parkingLotId)) {
                return parkingLot;
            }
        }
        return null;
    }

    Car getCar(Ticket ticket) {
        Car car = null;
        for (ParkingLot parkingLot : parkingLots) {
            car = parkingLot.getCar(ticket);
        }
        return car;
    }

    abstract Ticket park(Car car);

}
