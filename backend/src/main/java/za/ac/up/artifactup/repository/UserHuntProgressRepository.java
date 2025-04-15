package za.ac.up.artifactup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.up.artifactup.entity.UserHuntProgress;

public interface UserHuntProgressRepository extends JpaRepository<UserHuntProgress, Long> {

    Optional<UserHuntProgress> findBySessionIdAndHuntId(String sessionId, Long huntId);
}
