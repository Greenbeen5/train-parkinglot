package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.enums.ParkingStrategyEnum;
import li.lizhou.report.ReportVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractParkingBoy implements ParkingBoy {

    protected ParkingStrategyEnum parkingStrategy;

    protected List<Car> parkedCars;

    public AbstractParkingBoy() {
        parkedCars = new ArrayList<>();
    }

    @Override
    public ParkingStrategyEnum getParkingStrategy() {
        return parkingStrategy;
    }

    @Override
    public int countCars(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .mapToInt(ParkingLot::getSize)
                .sum();
    }

    @Override
    public Car getCar(Ticket ticket, List<ParkingLot> parkingLots) {
        Car car = parkingLots
                .stream()
                .map(parkingLot -> parkingLot.getCar(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow();
        removeParkedCar(car);
        return car;
    }

    protected void recordParkedCar(Car car) {
        parkedCars.add(car);
    }

    private void removeParkedCar(Car car) {
        parkedCars.remove(car);
    }

    public List<Car> getParkedCars() {
        return parkedCars;
    }

    @Override
    public void acceptReportVisitor(ReportVisitor reportVisitor) {
        reportVisitor.visit(this);
    }

    abstract public Ticket park(Car car, List<ParkingLot> parkingLots);

}
