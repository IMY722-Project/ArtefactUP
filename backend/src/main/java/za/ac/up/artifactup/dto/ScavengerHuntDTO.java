package za.ac.up.artifactup.dto;

import java.util.List;

import lombok.Data;

@Data
public class ScavengerHuntDTO {

    private Long id;
    private String name;
    private String description;
    private List<ScavengerHuntStepDTO> steps;
}
