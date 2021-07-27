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


class SuperParkingBoyTest {
    public static final int CAPACITY = 10;
    List<ParkingLot> parkingLots;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;
    ParkingBoy parkingBoy;

    @BeforeEach
    public void setUpParkingLot(){
        parkingLots = new ArrayList<>();
        parkingLot1 = new ParkingLot(1, CAPACITY * 2);
        parkingLot2 = new ParkingLot(2, CAPACITY);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingBoy = new SuperParkingBoy();
    }

    @Test
    public void should_park_to_parking_lot_with_lower_occupancy_rate(){
        for (int i = 0; i < CAPACITY; ++i){
            parkingLot1.park(CarBuilderHelper.randomCar().build()); // capacity: 20, size: 10
        }
        for (int i = 0; i < CAPACITY / 3; ++i){
            parkingLot2.park(CarBuilderHelper.randomCar().build()); // capacity: 10, size: 3
        }
        // System.out.println(parkingLot1.getSize() + " " + parkingLot2.getSize());
        Ticket ticket = parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build(), parkingLots);

        Assertions.assertEquals(CAPACITY + CAPACITY / 3 + 1, parkingBoy.countCars(parkingLots));
        Assertions.assertEquals(CAPACITY, parkingLot1.getSize());
        Assertions.assertEquals(CAPACITY / 3 + 1, parkingLot2.getSize());
        Assertions.assertEquals("川A7Y2B0", parkingBoy.getCar(ticket, parkingLots).getCarNumber());
    }

    @Test
    public void should_throw_exception_when_all_are_full(){
        for (int i = 0; i < CAPACITY * 3; ++i){
            parkingBoy.park(CarBuilderHelper.randomCar().build(), parkingLots);
        }
        Assertions.assertThrows(NotEnoughParkingSpaceException.class,
                () -> parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build(), parkingLots));
    }
}
