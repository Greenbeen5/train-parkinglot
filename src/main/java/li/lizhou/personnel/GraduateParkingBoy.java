package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;

public class GraduateParkingBoy extends AbstractParkingBoy {

    @Override
    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()){
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughParkingSpaceException("All the parking lots are full");
    }

}
