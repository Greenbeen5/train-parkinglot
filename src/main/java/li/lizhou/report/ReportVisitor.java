package li.lizhou.report;

import li.lizhou.personnel.ParkingBoy;
import lombok.Getter;

public class ReportVisitor {

    @Getter
    private final Report report;

    public ReportVisitor() {
        report = new Report();
    }

    public void visit(ParkingBoy boy) {
        report.addMapping(boy, boy.getParkedCars());
    }
}
