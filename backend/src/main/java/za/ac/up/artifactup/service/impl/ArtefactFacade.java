package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.up.artifactup.dto.mappers.ArtefactMapper;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.service.ArtefactService;

import java.util.List;

/**
 * An implementation of ArtefactService to be used with DTO's.
 * Handles the mapping between domain entities and DTO's.
 */
@Service
@RequiredArgsConstructor
public class ArtefactFacade implements ArtefactService<ArtefactDTO> {

  private final ArtefactService<Artefact> artefactService;

  private final ArtefactMapper mapper;

  @Override
  public List<ArtefactDTO> findAll() {
    return mapper.toDTOs(artefactService.findAll());
  }

  @Override
  public ArtefactDTO saveArtefact(ArtefactDTO artefact) {
    return mapper.toDTO(artefactService.saveArtefact(mapper.toEntity(artefact)));
  }

  @Override
  public List<ArtefactDTO> findAllArtifactsByCategoryId(Long categoryId) {
    return mapper.toDTOs(artefactService.findAllArtifactsByCategoryId(categoryId));
  }
}
