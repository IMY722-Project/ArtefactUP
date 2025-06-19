package za.ac.up.artefactup.service.impl;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.ArtefactDTO;
import za.ac.up.artefactup.entity.Artefact;
import za.ac.up.artefactup.entity.Collection;
import za.ac.up.artefactup.repository.CollectionRepository;
import za.ac.up.artefactup.service.ArtefactService;
import za.ac.up.artefactup.service.CollectionService;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
