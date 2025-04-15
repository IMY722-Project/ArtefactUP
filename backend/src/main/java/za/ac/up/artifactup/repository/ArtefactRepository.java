package za.ac.up.artifactup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.up.artifactup.entity.Artefact;

@Repository
public interface ArtefactRepository extends JpaRepository<Artefact, Long> {

    List<Artefact> findByCollectionName(String collectionName);

    List<Artefact> findAllByMuseumName(String museumName);

    Optional<Artefact> findByTitle(String name);
}
