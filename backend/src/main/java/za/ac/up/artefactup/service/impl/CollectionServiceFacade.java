package za.ac.up.artefactup.service.impl;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.CollectionDTO;
import za.ac.up.artefactup.dto.mapper.CollectionMapper;
import za.ac.up.artefactup.entity.Collection;
import za.ac.up.artefactup.service.CollectionService;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionServiceFacade implements CollectionService<CollectionDTO> {

  private final CollectionService<Collection> service;

  private final CollectionMapper mapper;

  @Override
  public CollectionDTO saveCollection(CollectionDTO collection) {
    return mapper.toDTO(service.saveCollection(mapper.toEntity(collection)));
  }

  @Override
  public Optional<CollectionDTO> findByName(String name) {
    return Optional.of(mapper.toDTO(service.findByName(name).get()));
  }

}
