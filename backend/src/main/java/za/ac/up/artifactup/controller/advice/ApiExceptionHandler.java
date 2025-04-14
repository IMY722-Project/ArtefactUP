package za.ac.up.artifactup.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import za.ac.up.artifactup.dto.ApiError;
import za.ac.up.artifactup.service.exceptions.NotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(
                exception.getMessage(),
                "NOT_FOUND",
                "Resource Not Found",
                exception.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(
                exception.getMessage(),
                "INTERNAL_SERVER_ERROR",
                "Internal Server Error",
                exception.getMessage()
        ));
    }
}
