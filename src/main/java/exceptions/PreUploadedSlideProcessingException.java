package exceptions;

public class PreUploadedSlideProcessingException extends RuntimeException {

    public PreUploadedSlideProcessingException(String message) {
        super(message);
    }

    public PreUploadedSlideProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
