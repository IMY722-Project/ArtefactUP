package za.ac.up.artifactup.service;

import java.util.List;

public interface ArtefactService<T> {

  List<T> findAll();

  T saveArtefact(T artefact);

  List<T> findAllArtifactsByCategoryId(Long categoryId);
}
