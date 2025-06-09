package za.ac.up.artefactup.dto;

import lombok.Data;

@Data
public class UserHuntProgressDTO {

    private long id;
    private String sessionId;
    private String scavengerHuntName;
    private ScavengerHuntStepDTO currentStep;
    private boolean completed;
}
