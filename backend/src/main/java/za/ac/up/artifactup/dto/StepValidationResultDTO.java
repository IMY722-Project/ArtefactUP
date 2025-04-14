package za.ac.up.artifactup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StepValidationResultDTO {

    private boolean matched;
    private boolean completed;
    private ScavengerHuntStepDTO scavengerHuntStep;
    private String message;
}
