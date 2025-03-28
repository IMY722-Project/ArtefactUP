package za.ac.up.artifactup.dto;


import lombok.*;

import java.util.*;

@Data
public class MuseumDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private List<ArtefactDTO> artefacts;
    private List<OpeningHoursDTO> openingHours;
    private String imageUrl;
}
