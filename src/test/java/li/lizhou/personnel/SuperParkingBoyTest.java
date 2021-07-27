package li.lizhou.personnel;


import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;
import li.lizhou.helper.CarBuilderHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SuperParkingBoyTest {
    public static final int CAPACITY = 10;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;
    AbstractParkingBoy parkingBoy;

    @BeforeEach
    public void setUpParkingLot(){
        parkingLot1 = new ParkingLot(1, CAPACITY * 2);
        parkingLot2 = new ParkingLot(2, CAPACITY);
        parkingBoy = new SuperParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
    }

    @Test
    public void should_park_to_parking_lot_with_lower_occupancy_rate(){
        CarBuilderHelper carBuilderHelper = new CarBuilderHelper();
        for (int i = 0; i < CAPACITY; ++i){
            parkingLot1.park(carBuilderHelper.randomCar().build()); // capacity: 20, size: 10
        }
        for (int i = 0; i < CAPACITY / 3; ++i){
            parkingLot2.park(carBuilderHelper.randomCar().build()); // capacity: 10, size: 3
        }
        // System.out.println(parkingLot1.getSize() + " " + parkingLot2.getSize());
        Ticket ticket = parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build());

        Assertions.assertEquals(CAPACITY + CAPACITY / 3 + 1, parkingBoy.countCars());
        Assertions.assertEquals(CAPACITY, parkingBoy.getParkingLot(1).getSize());
        Assertions.assertEquals(CAPACITY / 3 + 1, parkingBoy.getParkingLot(2).getSize());
        Assertions.assertEquals("川A7Y2B0", parkingBoy.getCar(ticket).getCarNumber());
    }

    @Test
    public void should_throw_exception_when_all_are_full(){
        CarBuilderHelper carBuilderHelper = new CarBuilderHelper();
        for (int i = 0; i < CAPACITY * 3; ++i){
            parkingBoy.park(carBuilderHelper.randomCar().build());
        }
        Assertions.assertThrows(NotEnoughParkingSpaceException.class,
                () -> parkingBoy.park(Car.builder().carNumber("川A7Y2B0").build()));
    }
}
