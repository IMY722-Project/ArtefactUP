package za.ac.up.artefactup.dto;

import lombok.*;

@Data
public class OpeningHoursDTO {
    private Long id;
    private String day;
    private String openingTime;
    private String closingTime;
}
