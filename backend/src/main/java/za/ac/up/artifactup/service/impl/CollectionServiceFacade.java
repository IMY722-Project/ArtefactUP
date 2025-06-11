package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.dto.CollectionDTO;
import za.ac.up.artifactup.dto.mapper.CollectionMapper;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.service.CollectionService;

import java.util.Optional;

@Service
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
