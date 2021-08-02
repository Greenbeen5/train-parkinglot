package li.lizhou.report;

import li.lizhou.domain.Car;
import li.lizhou.personnel.ParkingBoy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Report {

    @Getter
    private final Map<ParkingBoy, List<Car>> boyParkedCarMapping;

    public Report() {
        boyParkedCarMapping = new HashMap<>();
    }

    public void addMapping(ParkingBoy boy, List<Car> cars) {
        boyParkedCarMapping.put(boy, List.copyOf(cars));
    }

    private String generateMappingRecord(Map.Entry<ParkingBoy, List<Car>> entry) {
        StringBuilder sb = new StringBuilder();
        ParkingBoy boy = entry.getKey();
        sb.append("Parking boy hashcode: ")
                .append(boy.hashCode())
                .append(", Parking strategy: ")
                .append(boy.getParkingStrategy().toString())
                .append("\n\tParked cars:\n");
        entry.getValue()
                .stream()
                .map(Car::getCarNumber)
                .forEach((s -> sb.append("\t\t").append(s).append("\n")));
        sb.deleteCharAt(sb.lastIndexOf("\n")); // delete the trailing line break
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---PARKING BOYS REPORT---\n");
        new ArrayList<>(boyParkedCarMapping.entrySet())
                .stream()
                .map((this::generateMappingRecord))
                .collect(Collectors.toList())
                .forEach((record) -> {
                    sb.append(record);
                    sb.append("\n");
                });
        return sb.toString();
    }
}
