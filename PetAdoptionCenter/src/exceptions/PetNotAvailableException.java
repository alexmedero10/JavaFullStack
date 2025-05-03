package exceptions;

public class PetNotAvailableException extends RuntimeException {
    public PetNotAvailableException(String message) {
        super(message);
    }
}
