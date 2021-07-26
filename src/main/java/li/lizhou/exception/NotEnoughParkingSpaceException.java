package li.lizhou.exception;

public class NotEnoughParkingSpaceException extends RuntimeException {
    private static final long serialVersionUID = 6425378365285885296L;

    public NotEnoughParkingSpaceException(String message) {
        super(message);
    }
}
