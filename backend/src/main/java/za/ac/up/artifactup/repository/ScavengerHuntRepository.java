package za.ac.up.artifactup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.up.artifactup.entity.ScavengerHunt;

/**
 * Basic CRUD for ScavengerHunt.
 */
public interface ScavengerHuntRepository extends JpaRepository<ScavengerHunt, Long> {

    Optional<ScavengerHunt> findByName(String name);
}
