package za.ac.up.artifactup.service.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String progressNotFound) {
        super(progressNotFound);
    }
}
