package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;

import java.util.List;

public class GraduateParkingBoy extends AbstractParkingBoy {

    @Override
    public Ticket park(Car car, List<ParkingLot> parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()){
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughParkingSpaceException("All the parking lots are full");
    }

}
