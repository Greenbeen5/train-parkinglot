package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;
import li.lizhou.helper.CarBuilderHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SmartParkingBoyTest {

    public static final int CAPACITY = 10;
    List<ParkingLot> parkingLots;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;
    ParkingBoy parkingBoy;

    @BeforeEach
    public void setUpParkingLot(){
        parkingLots = new ArrayList<>();
        parkingLot1 = new ParkingLot(1, CAPACITY);
        parkingLot2 = new ParkingLot(2, CAPACITY);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingBoy = new SmartParkingBoy();
    }

    @Test
    public void should_park_to_parking_lot_with_more_spaces(){
        CarBuilderHelper carBuilderHelper = new CarBuilderHelper();
        for (int i = 0; i < CAPACITY / 2; ++i){
            parkingLot1.park(carBuilderHelper.randomCar().build());
        }
        Ticket ticket = parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build(), parkingLots);

        Assertions.assertEquals(CAPACITY / 2 + 1, parkingBoy.countCars(parkingLots));
        Assertions.assertEquals(CAPACITY / 2, parkingLot1.getSize());
        Assertions.assertEquals(1, parkingLot2.getSize());
        Assertions.assertEquals("川A7Y2B0", parkingBoy.getCar(ticket, parkingLots).getCarNumber());
    }

    @Test
    public void should_throw_exception_when_all_are_full(){
        CarBuilderHelper carBuilderHelper = new CarBuilderHelper();
        for (int i = 0; i < CAPACITY * 2; ++i){
            parkingBoy.park(carBuilderHelper.randomCar().build(), parkingLots);
        }
        Assertions.assertThrows(NotEnoughParkingSpaceException.class,
                () -> parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build(), parkingLots));
    }

}
