package za.ac.up.artifactup.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.dto.ArtefactDTO;
import za.ac.up.artifactup.dto.mapper.ArtefactMapperImpl;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.service.ArtefactService;

/**
 * An implementation of ArtefactService to be used with DTO's.
 * Handles the mapping between domain entities and DTO's.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtefactServiceFacade implements ArtefactService<ArtefactDTO> {

    private final ArtefactService<Artefact> service;

    private final ArtefactMapperImpl mapper;

    @Override
    public List<ArtefactDTO> findAll() {
        return mapper.toDTOs(service.findAll());
    }

    @Override
    @Transactional
    public ArtefactDTO create(ArtefactDTO dto) {
        return mapper.toDTO(service.create(mapper.toEntity(dto)));
    }

    @Override
    public List<ArtefactDTO> findAllArtifactsByCollectionName(String collectionName) {
        return mapper.toDTOs(service.findAllArtifactsByCollectionName(collectionName));
    }

    @Override
    public List<ArtefactDTO> findAllArtefactsByMuseumName(String museumName) {
        return mapper.toDTOs(service.findAllArtefactsByMuseumName(museumName));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        service.deleteById(id);
    }

    @Override
    public Optional<ArtefactDTO> findByName(final String name) {
        return Optional.empty();
    }
}
