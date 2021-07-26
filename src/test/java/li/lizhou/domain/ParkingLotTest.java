package li.lizhou.domain;

import li.lizhou.exception.CarNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {

    @Test
    public void should_park_and_get_car_success(){
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car("川A18023");
        Ticket ticket = parkingLot.park(car);
        car = parkingLot.getCar(ticket);

        assertEquals("川A18023", car.getCarNumber());
    }

    @Test
    public void should_get_car_without_parking_fail(){
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = new Ticket();
        assertThrows(CarNotFoundException.class, () -> parkingLot.getCar(ticket));
    }

}
