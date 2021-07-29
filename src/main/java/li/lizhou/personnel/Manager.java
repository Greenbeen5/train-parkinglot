package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.enums.ParkingStrategyEnum;
import li.lizhou.exception.NotEnoughParkingSpaceException;
import li.lizhou.report.Report;
import li.lizhou.report.ReportVisitor;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static li.lizhou.enums.ParkingStrategyEnum.MANAGER;


public class Manager {

    private final ParkingStrategyEnum parkingStrategy = MANAGER;

    private final List<ParkingLot> parkingLots;

    private final List<ParkingBoy> parkingBoys;

    public Manager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        this.parkingLots = parkingLots;
        this.parkingBoys = parkingBoys;
    }

    public Ticket park(Car car, ParkingStrategyEnum strategy) {
        if (this.parkingStrategy.equals(strategy)) {
            return park(car);
        }
        return parkingBoys
                .stream()
                .filter(parkingBoy -> parkingBoy.getParkingStrategy().equals(strategy))
                .findAny()
                .orElseThrow()
                .park(car, parkingLots);
    }

    public Ticket park(Car car) {
        // The manager will try to park the car
        // at the biggest parking lot
        // (in other words, the first possible
        // parking lot ordered by capacity descending).
        return parkingLots.stream()
                .filter(Predicate.not(ParkingLot::isFull))
                .max(Comparator.comparingInt(ParkingLot::getCapacity))
                .orElseThrow(() -> new NotEnoughParkingSpaceException("All the parking lots are full"))
                .park(car);
    }

    public ParkingLot getParkingLot(int id) {
        return parkingLots.stream()
                .filter((p) -> p.getId().equals(id))
                .findAny()
                .orElseThrow();
    }

    public Report generateReport(ReportVisitor visitor) {
        parkingBoys.forEach((p) -> p.acceptReportVisitor(visitor));
        return visitor.getReport();
    }
}
