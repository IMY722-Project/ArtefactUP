package za.ac.up.artefactup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import za.ac.up.artefactup.entity.UserHuntProgress;

public interface UserHuntProgressRepository extends JpaRepository<UserHuntProgress, Long> {

    Optional<UserHuntProgress> findBySessionIdAndHuntId(String sessionId, Long huntId);

    long countBySessionId(String sessionId); // hunts attempted

    long countBySessionIdAndCompletedTrue(String sessionId); // hunts completed

    @Query("""
                SELECT SUM(
                    CASE\s
                        WHEN h.completed = true THEN h.currentStep\s
                        ELSE h.currentStep - 1\s
                    END
                )\s
                FROM UserHuntProgress h WHERE h.sessionId = :sessionId
            """)
    Long sumOfArtefactsFound(@Param("sessionId") String sessionId);

    Optional<List<UserHuntProgress>> findAllBySessionId(String sessionId);

}
