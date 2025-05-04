package za.ac.up.artifactup.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.dto.ScavengerHuntDTO;
import za.ac.up.artifactup.dto.mapper.ScavengerHuntMapperImpl;
import za.ac.up.artifactup.entity.ScavengerHunt;
import za.ac.up.artifactup.service.ScavengerHuntService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScavengerHuntServiceFacade implements ScavengerHuntService<ScavengerHuntDTO> {

    private final ScavengerHuntService<ScavengerHunt> service;
    private final ScavengerHuntMapperImpl mapper;

    @Override
    @Transactional
    public List<ScavengerHuntDTO> findAll() {
        return service.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ScavengerHuntDTO create(ScavengerHuntDTO dto) {
        return Optional.ofNullable(service.create(mapper.toEntity(dto))).map(mapper::toDto).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        service.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<ScavengerHuntDTO> findByName(String name) {
        return service.findByName(name).map(mapper::toDto);
    }

    @Override
    public Optional<ScavengerHuntDTO> findById(final Long id) {
        return service.findById(id)
                .map(mapper::toDto);
    }
}
