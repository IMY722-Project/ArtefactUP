package za.ac.up.artefactup.service;

import java.util.List;
import java.util.Optional;

public interface ArtefactService<T> {

    List<T> findAll();

    T create(T artefact);

    List<T> findAllArtifactsByCollectionName(String collectionName);

    List<T> findAllArtefactsByMuseumName(String museumName);

    void deleteById(Long id);

    Optional<T> findByTitle(String name);
}
