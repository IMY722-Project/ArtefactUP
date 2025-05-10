package za.ac.up.artifactup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserHuntStatsDTO {

    private int huntsAttempted;
    private int huntsCompleted;
    private int artefactsFound;
    private int totalArtefactsAvailable;
}
