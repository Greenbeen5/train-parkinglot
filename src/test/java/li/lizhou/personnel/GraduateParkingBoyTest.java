package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;
import li.lizhou.helper.CarBuilderHelper;
import li.lizhou.personnel.GraduateParkingBoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {

    public static final int CAPACITY = 10;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;
    ParkingBoy parkingBoy;

    @BeforeEach
    public void setUpParkingLot(){
        parkingLot1 = new ParkingLot(1, CAPACITY);
        parkingLot2 = new ParkingLot(2, CAPACITY);
        parkingBoy = new GraduateParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
    }

    @Test
    public void should_park_to_another_parking_lot_when_one_is_full(){
        CarBuilderHelper carBuilderHelper = new CarBuilderHelper();
        for (int i = 0; i < CAPACITY; ++i){
            parkingBoy.park(carBuilderHelper.randomCar().build());
        }
        Ticket ticket = parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build());
        assertEquals(CAPACITY + 1, parkingBoy.countCars());
        assertEquals(1, parkingBoy.getParkingLot(2).getSize());
        assertEquals("川A7Y2B0", parkingBoy.getCar(ticket).getCarNumber());
        assertEquals(0, parkingBoy.getParkingLot(2).getSize());
    }

    @Test
    public void should_throw_exception_when_all_are_full(){
        CarBuilderHelper carBuilderHelper = new CarBuilderHelper();
        for (int i = 0; i < CAPACITY * 2; ++i){
            parkingBoy.park(carBuilderHelper.randomCar().build());
        }
        assertThrows(NotEnoughParkingSpaceException.class,
                () -> parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build()));
    }
}
