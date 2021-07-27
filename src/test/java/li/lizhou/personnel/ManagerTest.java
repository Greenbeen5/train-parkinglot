package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.helper.CarBuilderHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ManagerTest {

    private Manager manager;
    public static final int CAPACITY = 10;
    List<ParkingLot> parkingLots;
    ParkingBoy graduateBoy;
    ParkingBoy smartBoy;
    ParkingBoy superBoy;

    @BeforeEach
    public void setUp() {
        parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1, CAPACITY * 2));
        parkingLots.add(new ParkingLot(2, CAPACITY));
        manager = new Manager(parkingLots);
        graduateBoy = new GraduateParkingBoy();
        smartBoy = new SmartParkingBoy();
        superBoy = new SuperParkingBoy();
        CarBuilderHelper carBuilderHelper = new CarBuilderHelper();
        for (int i = 0; i < CAPACITY / 2; ++i) {
            manager.getParkingLot(1).park(carBuilderHelper.randomCar().build()); // 5 of 20 are used
        }
    }

    @Test
    public void should_ask_graduate_parking_boy_to_park() {

        Ticket ticket = manager.park(Car.builder().carNumber("京G12L9P").build(), graduateBoy);

        Assertions.assertEquals(CAPACITY / 2 + 1, graduateBoy.countCars(parkingLots));
        Assertions.assertEquals(CAPACITY / 2 + 1, manager.getParkingLot(1).getSize());
        Assertions.assertEquals(0, manager.getParkingLot(2).getSize());
        Assertions.assertEquals("京G12L9P", graduateBoy.getCar(ticket, parkingLots).getCarNumber());
    }

    @Test
    public void should_ask_smart_parking_boy_to_park() {

        Ticket ticket = manager.park(Car.builder().carNumber("京G12L9P").build(), smartBoy);

        Assertions.assertEquals(CAPACITY / 2 + 1, smartBoy.countCars(parkingLots));
        Assertions.assertEquals(CAPACITY / 2 + 1, manager.getParkingLot(1).getSize());
        Assertions.assertEquals(0, manager.getParkingLot(2).getSize());
        Assertions.assertEquals("京G12L9P", smartBoy.getCar(ticket, parkingLots).getCarNumber());
    }

    @Test
    public void should_ask_super_parking_boy_to_park() {

        Ticket ticket = manager.park(Car.builder().carNumber("京G12L9P").build(), superBoy);

        Assertions.assertEquals(CAPACITY / 2 + 1, superBoy.countCars(parkingLots));
        Assertions.assertEquals(CAPACITY / 2, manager.getParkingLot(1).getSize());
        Assertions.assertEquals(1, manager.getParkingLot(2).getSize());
        Assertions.assertEquals("京G12L9P", superBoy.getCar(ticket, parkingLots).getCarNumber());
    }

    @Test
    public void should_park_by_manager() {

        Ticket ticket = manager.park(Car.builder().carNumber("京G12L9P").build());

        Assertions.assertEquals(CAPACITY / 2 + 1, superBoy.countCars(parkingLots));
        Assertions.assertEquals(CAPACITY / 2 + 1, manager.getParkingLot(1).getSize());
        Assertions.assertEquals(0, manager.getParkingLot(2).getSize());
        Assertions.assertEquals("京G12L9P", superBoy.getCar(ticket, parkingLots).getCarNumber());
    }
}
