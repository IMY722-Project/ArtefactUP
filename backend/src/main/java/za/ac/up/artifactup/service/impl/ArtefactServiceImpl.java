package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.dto.mapper.ArtefactMapper;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.repository.ArtefactRepository;
import za.ac.up.artifactup.service.ArtefactService;
import za.ac.up.artifactup.service.CollectionService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtefactServiceImpl implements ArtefactService<Artefact> {

  private final ArtefactRepository repository;

  private final CollectionService<Collection> collectionService;

  private final ArtefactMapper artefactMapper;

  @Override
  public List<Artefact> findAll() {
    return repository.findAll();
  }

  @Override
  @Transactional
  public Artefact create(Artefact artefact) {
    Collection collection = artefact.getCollection();

    Optional<Collection> foundCollection = collectionService.findByName(collection.getName().trim());


    if (foundCollection.isPresent()) {
      collection = foundCollection.get();
    } else {
      collection = collectionService.saveCollection(collection);
    }
    artefact.setCollection(collection);
    return repository.save(artefact);
  }

  @Override
  public List<Artefact> findAllArtifactsByCollectionName(String collectionName) {
    return repository.findByCollectionName(collectionName);
  }

  @Override
  public List<Artefact> findAllArtifactsByMuseumName(String museumName) {
    return repository.findAllByMuseumName(museumName);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

}
