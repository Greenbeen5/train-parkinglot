package li.lizhou.helper;

import li.lizhou.domain.Car;
import org.apache.commons.lang3.RandomStringUtils;

public class CarBuilderHelper {

    private static final int CAR_NUMBER_LENGTH = 7;

    public Car.CarBuilder randomCar() {
        return Car.builder().carNumber(RandomStringUtils.random(CAR_NUMBER_LENGTH));
    }

}
