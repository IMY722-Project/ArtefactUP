package za.ac.up.artefactup.dto;

import lombok.Data;

@Data
public class ScavengerHuntStepDTO {

    private Long id;
    private String scavengerHuntName;
    private int stepNumber;
    private ArtefactDTO artefact;
    private String museumName;
    private String clue;
    private String hint;
}
