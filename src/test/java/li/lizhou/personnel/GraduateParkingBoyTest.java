package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;
import li.lizhou.helper.CarBuilderHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {

    public static final int CAPACITY = 10;
    List<ParkingLot> parkingLots;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;
    ParkingBoy parkingBoy;

    @BeforeEach
    public void setUpParkingLot() {
        parkingLots = new ArrayList<>();
        parkingLot1 = new ParkingLot(1, CAPACITY);
        parkingLot2 = new ParkingLot(2, CAPACITY);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingBoy = new GraduateParkingBoy();
    }

    @Test
    public void should_park_to_another_parking_lot_when_one_is_full() {
        for (int i = 0; i < CAPACITY; ++i) {
            parkingBoy.park(CarBuilderHelper.randomCar().build(), parkingLots);
        }
        Ticket ticket = parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build(), parkingLots);
        assertEquals(CAPACITY + 1, parkingBoy.countCars(parkingLots));
        assertEquals(1, parkingLot2.getSize());
        assertEquals("川A7Y2B0", parkingBoy.getCar(ticket, parkingLots).getCarNumber());
        assertEquals(0, parkingLot2.getSize());
    }

    @Test
    public void should_throw_exception_when_all_are_full() {
        for (int i = 0; i < CAPACITY * 2; ++i) {
            parkingBoy.park(CarBuilderHelper.randomCar().build(), parkingLots);
        }
        assertThrows(NotEnoughParkingSpaceException.class,
                () -> parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build(), parkingLots));
    }

    @Test
    public void should_throw_exception_when_ticket_is_invalid() {
        assertThrows(NoSuchElementException.class, () -> parkingBoy.getCar(new Ticket(), parkingLots));
    }
}
