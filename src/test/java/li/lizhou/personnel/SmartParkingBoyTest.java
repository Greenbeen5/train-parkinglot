package li.lizhou.personnel;

import li.lizhou.domain.ParkingLot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SmartParkingBoyTest {

    public static final int CAPACITY = 10;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;
    ParkingBoy parkingBoy;

    @BeforeEach
    public void setUpParkingLot(){
        parkingLot1 = new ParkingLot(1, CAPACITY);
        parkingLot2 = new ParkingLot(2, CAPACITY);
        parkingBoy = new SmartParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);
    }

    @Test
    public void should_park_to_parking_lot_with_more_spaces(){

    }

}
