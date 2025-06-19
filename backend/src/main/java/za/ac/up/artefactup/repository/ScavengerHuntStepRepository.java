package za.ac.up.artefactup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import za.ac.up.artefactup.entity.ScavengerHuntStep;

public interface ScavengerHuntStepRepository extends JpaRepository<ScavengerHuntStep, Long> {

    Optional<ScavengerHuntStep> findByHuntIdAndStepNumber(Long huntId, int stepNumber);

    Optional<ScavengerHuntStep> findScavengerHuntStepByHuntName(String name);

    List<ScavengerHuntStep> findByHuntIdOrderByStepNumberAsc(long id);

    @Query("SELECT COUNT(s) FROM ScavengerHuntStep s")
    long countAllSteps();

}
