package za.ac.up.artifactup.dto;

import lombok.Data;

@Data
public class ScavengerHuntStepDTO {

    private Long id;
    private String scavengerHuntName;
    private int stepNumber;
    private ArtefactDTO artefact;
    private String museumName;
    private String clue;
}
