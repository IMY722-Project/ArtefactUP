package za.ac.up.artifactup.service;

public interface UserHuntProgressService<T> {

    T getUserProgress(Long huntId, String sessionId);

    T saveUserProgress(T userHuntProgress);

    T startHunt(Long huntId, String sessionId);

}
