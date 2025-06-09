package za.ac.up.artefactup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.up.artefactup.entity.Artefact;

@Repository
public interface ArtefactRepository extends JpaRepository<Artefact, Long> {

    List<Artefact> findByCollectionName(String collectionName);

    List<Artefact> findAllByMuseumName(String museumName);

    Optional<Artefact> findByTitle(String name);
}
