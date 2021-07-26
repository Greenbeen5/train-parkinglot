package li.lizhou.domain;

import lombok.Getter;

@Getter
public class Car {

    private String carNumber;

    public Car(String carNumber) {
        this.carNumber = carNumber;
    }
}
