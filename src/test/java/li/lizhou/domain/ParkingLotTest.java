package li.lizhou.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {

    @Test
    public void should_park_and_get_car_success() {
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car("川A18023");
        Ticket ticket = parkingLot.park(car);
        car = parkingLot.getCar(ticket);

        assertEquals("川A18023", car.getCarNumber());
    }

    @Test
    public void should_get_car_without_parking_fail() {
        ParkingLot parkingLot = new ParkingLot(0);
        Ticket ticket = new Ticket();
        assertNull(parkingLot.getCar(ticket));
    }

    @Test
    public void should_throw_exception_when_capacity_is_negative() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0, -5));
    }
}
