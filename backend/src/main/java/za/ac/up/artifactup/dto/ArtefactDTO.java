package za.ac.up.artifactup.dto;

import lombok.*;
import za.ac.up.artifactup.entity.*;

@Data
public class ArtefactDTO {
    private Long id;
    private String title;
    private String creator;
    private String description;
    private String collectionName;
    private String museumName;
    private String dateCreated;
    private String locationCreated;
    private String physicalDimensions;
    private ArtefactType type;
    private String rights;
    private String medium;
}
