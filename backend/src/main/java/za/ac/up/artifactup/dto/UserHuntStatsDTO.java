package za.ac.up.artifactup.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserHuntStatsDTO {

    private int huntsAttempted;
    private int huntsCompleted;
    private int artefactsFound;
    private int totalArtefactsAvailable;
    private int totalHuntsAvailable;
    private List<Integer> attemptedHuntIds;
}
