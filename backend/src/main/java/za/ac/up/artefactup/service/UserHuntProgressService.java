package za.ac.up.artefactup.service;

import za.ac.up.artefactup.dto.UserHuntStatsDTO;

public interface UserHuntProgressService<T> {

    T getUserProgress(Long huntId, String sessionId);

    T saveUserProgress(T userHuntProgress);

    T startHunt(Long huntId, String sessionId);

    UserHuntStatsDTO getUserHuntStats(String sessionId);

}
