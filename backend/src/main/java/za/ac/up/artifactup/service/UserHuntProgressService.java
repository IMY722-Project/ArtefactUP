package za.ac.up.artifactup.service;

import za.ac.up.artifactup.dto.UserHuntStatsDTO;

public interface UserHuntProgressService<T> {

    T getUserProgress(Long huntId, String sessionId);

    T saveUserProgress(T userHuntProgress);

    T startHunt(Long huntId, String sessionId);

    UserHuntStatsDTO getUserHuntStats(String sessionId);

}
