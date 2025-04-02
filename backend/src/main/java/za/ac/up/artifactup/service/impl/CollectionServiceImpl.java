package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.repository.CollectionRepository;
import za.ac.up.artifactup.service.ArtefactService;
import za.ac.up.artifactup.service.CollectionService;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService<Collection> {

  private final CollectionRepository repository;

  @Override
  @Transactional
  public Collection saveCollection(Collection collection) {
    return repository.save(collection);
  }

  @Override
  public Optional<Collection> findByName(String name) {
    return repository.findByName(name);
  }
}
