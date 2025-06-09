package za.ac.up.artefactup.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.ArtefactDTO;
import za.ac.up.artefactup.dto.mapper.ArtefactMapper;
import za.ac.up.artefactup.entity.Artefact;
import za.ac.up.artefactup.service.ArtefactService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * An implementation of ArtefactService to be used with DTO's.
 * Handles the mapping between domain entities and DTO's.
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtefactServiceFacade implements ArtefactService<ArtefactDTO> {

    private final ArtefactService<Artefact> service;

    private final ArtefactMapper mapper;

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
    public Optional<ArtefactDTO> findByTitle(final String name) {
        return Optional.empty();
    }
}
