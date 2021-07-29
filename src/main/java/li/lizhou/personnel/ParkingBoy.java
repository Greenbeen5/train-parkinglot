package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.enums.ParkingStrategyEnum;
import li.lizhou.report.ReportVisitor;

import java.util.List;

public interface ParkingBoy {

    ParkingStrategyEnum getParkingStrategy();

    int countCars(List<ParkingLot> parkingLots);

    Car getCar(Ticket ticket, List<ParkingLot> parkingLots);

    Ticket park(Car car, List<ParkingLot> parkingLots);

    List<Car> getParkedCars();

    void acceptReportVisitor(ReportVisitor reportVisitor);
}
