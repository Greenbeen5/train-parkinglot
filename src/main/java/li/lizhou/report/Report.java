package li.lizhou.report;

import li.lizhou.domain.Car;
import li.lizhou.personnel.ParkingBoy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Report {

    private final Map<ParkingBoy, List<Car>> boyParkedCarMapping;

    public Report() {
        boyParkedCarMapping = new HashMap<>();
    }

    public void addMapping(ParkingBoy boy, List<Car> cars) {
        boyParkedCarMapping.put(boy, cars);
    }

    private String generateMappingRecord(Map.Entry<ParkingBoy, List<Car>> entry) {
        StringBuilder sb = new StringBuilder();
        ParkingBoy boy = entry.getKey();
        sb.append("ParkingBoy: ")
                .append(boy.hashCode())
                .append(" Strategy: ")
                .append(boy.getParkingStrategy().toString())
                .append("\nCars:\n");
        entry.getValue()
                .stream()
                .map(Objects::toString)
                .forEach((s -> sb.append("\t").append(s).append("\n")));
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        new ArrayList<>(boyParkedCarMapping.entrySet())
                .stream()
                .map((this::generateMappingRecord))
                .collect(Collectors.toList())
                .forEach((r) -> {
                    sb.append(r);
                    sb.append("\n");
                });
        return sb.toString();
    }
}
