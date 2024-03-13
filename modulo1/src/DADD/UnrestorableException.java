package DADD;


public class UnrestorableException extends RuntimeException {
    public UnrestorableException(String message) {
        super(message);
    }

    public UnrestorableException(String message, Throwable cause) {
        super(message, cause);
    }
}
