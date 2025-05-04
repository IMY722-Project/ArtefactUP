package za.ac.up.artifactup.service.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(final String message) {
        super(message);
    }

}
