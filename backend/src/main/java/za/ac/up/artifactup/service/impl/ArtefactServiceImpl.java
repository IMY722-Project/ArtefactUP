package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.up.artifactup.dto.mappers.ArtefactMapper;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.repository.ArtefactRepository;
import za.ac.up.artifactup.service.ArtefactService;
import za.ac.up.artifactup.service.CollectionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtefactServiceImpl implements ArtefactService<Artefact> {

  private final ArtefactRepository artefactRepository;

  private final CollectionService collectionService;

  private final ArtefactMapper artefactMapper;

  @Override
  public List<Artefact> findAll() {
    return artefactRepository.findAll();
  }

  @Override
  public Artefact saveArtefact(Artefact artefact) {
    Collection collection = collectionService.findByName(artefact.getCollection().getName())
                                       .orElse(collectionService.saveCollection(artefact.getCollection()));
    artefact.setCollection(collection);
    return artefactRepository.save(artefact);
  }

  @Override
  public List<Artefact> findAllArtifactsByCategoryId(Long categoryId) {
    return artefactRepository.findByCategoryId(categoryId);
  }

}
