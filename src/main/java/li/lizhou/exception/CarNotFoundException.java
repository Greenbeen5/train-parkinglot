package li.lizhou.exception;

import java.io.Serializable;

public class CarNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 3443363431236207836L;

    public CarNotFoundException(String message) {
        super(message);
    }
}
