package za.ac.up.artifactup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiError {

    private String message;
    private String errorCode;
    private String errorType;
    private String errorDescription;

}
