package za.ac.up.artifactup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Museum;

import java.util.Optional;

public interface MuseumRepository extends JpaRepository<Museum,Long> {

  Optional<Museum> findByName(String name);
}
