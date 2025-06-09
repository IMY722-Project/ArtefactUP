package za.ac.up.artefactup.service.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String progressNotFound) {
        super(progressNotFound);
    }
}
