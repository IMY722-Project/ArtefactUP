package za.ac.up.artifactup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.up.artifactup.entity.ScavengerHuntStep;

/**
 * Handles step‑level look‑ups inside a hunt.
 */
public interface ScavengerHuntStepRepository extends JpaRepository<ScavengerHuntStep, Long> {

    Optional<ScavengerHuntStep> findByHuntIdAndStepNumber(Long huntId, int stepNumber);

    Optional<ScavengerHuntStep> findScavengerHuntStepByHuntName(String name);

    List<ScavengerHuntStep> findByHuntIdOrderByStepNumberAsc(long id);

}
