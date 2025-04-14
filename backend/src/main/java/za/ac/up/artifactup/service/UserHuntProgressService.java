package za.ac.up.artifactup.service;

import java.util.Optional;

public interface UserHuntProgressService<T> {

    Optional<T> getUserProgress(Long huntId, String sessionId);

    T saveUserProgress(T userHuntProgress);

}
