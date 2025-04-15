package za.ac.up.artifactup.dto;

import lombok.Data;

@Data
public class UserHuntProgressDTO {

    private long id;
    private String cognitoUserId;
    private String scavengerHuntName;
    private int currentStep;
    private boolean completed;
}
