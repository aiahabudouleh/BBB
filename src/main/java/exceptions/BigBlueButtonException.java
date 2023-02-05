package exceptions;

public class BigBlueButtonException extends RuntimeException {

    public BigBlueButtonException() {
    }

    public BigBlueButtonException(String message) {
        super(message);
    }

    public BigBlueButtonException(String message, Throwable cause) {
        super(message, cause);
    }
}
