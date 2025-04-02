package za.ac.up.artifactup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.up.artifactup.entity.Artefact;

import java.util.List;

@Repository
public interface ArtefactRepository extends JpaRepository<Artefact,Long> {

  List<Artefact> findByCollectionName(String collectionName);

  List<Artefact> findAllByMuseumName(String museumName);
}
