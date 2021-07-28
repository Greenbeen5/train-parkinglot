package li.lizhou.report;

import li.lizhou.personnel.ParkingBoy;
import lombok.Getter;

public class ReportVisitor {

    @Getter
    private Report report;

    public void visit(ParkingBoy boy){

    }
}
