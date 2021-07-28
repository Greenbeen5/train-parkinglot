package li.lizhou.personnel;

import li.lizhou.domain.Car;
import li.lizhou.domain.ParkingLot;
import li.lizhou.domain.Ticket;
import li.lizhou.enums.ParkingStrategyEnum;
import li.lizhou.report.ReportVisitor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

public abstract class AbstractParkingBoy implements ParkingBoy {

    protected static ParkingStrategyEnum parkingStrategy;

    @Override
    public ParkingStrategyEnum getParkingStrategy() {
        return parkingStrategy;
    }

    @Override
    public int countCars(List<ParkingLot> parkingLots){
        return parkingLots.stream()
                .mapToInt(ParkingLot::getSize)
                .sum();
    }

    @Override
    public Car getCar(Ticket ticket, List<ParkingLot> parkingLots) {
        return parkingLots
                .stream()
                .map(parkingLot -> parkingLot.getCar(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void getReport(ReportVisitor reportVisitor){

    }

    abstract public Ticket park(Car car, List<ParkingLot> parkingLots);

}
