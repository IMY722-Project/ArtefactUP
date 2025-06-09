package za.ac.up.artefactup.dto;

import lombok.*;

import java.util.*;

@Data
public class MuseumDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private List<OpeningHoursDTO> openingHours;
    private String imageUrl;
}
