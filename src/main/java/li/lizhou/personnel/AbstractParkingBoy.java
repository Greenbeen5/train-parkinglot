package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;

import java.util.List;

public abstract class AbstractParkingBoy implements ParkingBoy {

    public int countCars(List<ParkingLot> parkingLots){
        return parkingLots.stream()
                .mapToInt(ParkingLot::getSize)
                .sum();
    }

    public Car getCar(Ticket ticket, List<ParkingLot> parkingLots) {
        Car car = null;
        for (ParkingLot parkingLot : parkingLots) {
            if(null == car) {
                car = parkingLot.getCar(ticket);
            }
        }
        return car;
    }

    abstract public Ticket park(Car car, List<ParkingLot> parkingLots);

}
