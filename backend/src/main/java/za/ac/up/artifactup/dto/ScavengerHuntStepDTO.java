package za.ac.up.artifactup.dto;

import lombok.Data;

@Data
public class ScavengerHuntStepDTO {

    private Long id;
    private String scavengerHuntName;
    private int stepNumber;
    private String artefactName;
    private String museumName;
    private String clue;
    private String qrCode;
}
