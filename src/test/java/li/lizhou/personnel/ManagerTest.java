package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.exception.NotEnoughParkingSpaceException;
import li.lizhou.helper.CarBuilderHelper;
import li.lizhou.report.Report;
import li.lizhou.report.ReportVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static li.lizhou.enums.ParkingStrategyEnum.GRADUATE;
import static li.lizhou.enums.ParkingStrategyEnum.SMART;
import static li.lizhou.enums.ParkingStrategyEnum.SUPER;

class ManagerTest {

    public static final int CAPACITY = 10;
    List<ParkingLot> parkingLots;
    List<ParkingBoy> parkingBoys;
    ParkingBoy graduateBoy;
    ParkingBoy smartBoy;
    ParkingBoy superBoy;
    private Manager manager;

    @BeforeEach
    public void setUp() {
        parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1, CAPACITY * 2));
        parkingLots.add(new ParkingLot(2, CAPACITY));
        parkingBoys = new ArrayList<>();
        graduateBoy = new GraduateParkingBoy();
        smartBoy = new SmartParkingBoy();
        superBoy = new SuperParkingBoy();
        parkingBoys.add(graduateBoy);
        parkingBoys.add(smartBoy);
        parkingBoys.add(superBoy);
        manager = new Manager(parkingLots, parkingBoys);
        for (int i = 0; i < CAPACITY / 2; ++i) {
            manager.getParkingLot(1).park(CarBuilderHelper.randomCar().build()); // 5 of 20 are used
        }
    }

    @Test
    public void should_ask_graduate_parking_boy_to_park() {

        Ticket ticket = manager.park(Car.builder().carNumber("京G12L9P").build(), GRADUATE);

        Assertions.assertEquals(CAPACITY / 2 + 1, graduateBoy.countCars(parkingLots));
        Assertions.assertEquals(CAPACITY / 2 + 1, manager.getParkingLot(1).getSize());
        Assertions.assertEquals(0, manager.getParkingLot(2).getSize());
        Assertions.assertEquals("京G12L9P", graduateBoy.getCar(ticket, parkingLots).getCarNumber());
    }

    @Test
    public void should_ask_smart_parking_boy_to_park() {

        Ticket ticket = manager.park(Car.builder().carNumber("京G12L9P").build(), SMART);

        Assertions.assertEquals(CAPACITY / 2 + 1, smartBoy.countCars(parkingLots));
        Assertions.assertEquals(CAPACITY / 2 + 1, manager.getParkingLot(1).getSize());
        Assertions.assertEquals(0, manager.getParkingLot(2).getSize());
        Assertions.assertEquals("京G12L9P", smartBoy.getCar(ticket, parkingLots).getCarNumber());
    }

    @Test
    public void should_ask_super_parking_boy_to_park() {

        Ticket ticket = manager.park(Car.builder().carNumber("京G12L9P").build(), SUPER);

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

    @Test
    public void should_return_null_when_all_are_full() {
        for (int i = 0; i < CAPACITY * 3 - 5; ++i) {
            manager.park(CarBuilderHelper.randomCar().build());
        }
        Assertions.assertThrows(NotEnoughParkingSpaceException.class, () -> manager.park(Car.builder().carNumber("渝A12345").build()));
    }

    @Test
    public void should_generate_reports() {
        Car carA = Car.builder().carNumber("京A67U9P").build();
        Car carB = Car.builder().carNumber("鄂CB6789").build();
        Car carC = Car.builder().carNumber("桂D8S09L").build();
        manager.park(carA, GRADUATE);
        manager.park(carB, SMART);
        manager.park(carC, SUPER);

        ReportVisitor visitor = new ReportVisitor();
        Report parkingReport = manager.generateReport(visitor);
        // TODO finish assertions about the report
        System.out.println(parkingReport.toString());
    }
}
