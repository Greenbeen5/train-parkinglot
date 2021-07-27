package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParkingBoy implements ParkingBoy {

    protected final List<ParkingLot> parkingLots;

    public AbstractParkingBoy(){
        parkingLots = new ArrayList<>();
    }

    public void addParkingLot(ParkingLot parkingLot){
        parkingLots.add(parkingLot);
    }

    public int countCars(){
        return parkingLots.stream()
                .mapToInt(ParkingLot::getSize)
                .sum();
    }

    public ParkingLot getParkingLot(int parkingLotId) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getId().equals(parkingLotId)) {
                return parkingLot;
            }
        }
        return null;
    }

    public Car getCar(Ticket ticket) {
        Car car = null;
        for (ParkingLot parkingLot : parkingLots) {
            car = parkingLot.getCar(ticket);
        }
        return car;
    }

    abstract public Ticket park(Car car);

}
