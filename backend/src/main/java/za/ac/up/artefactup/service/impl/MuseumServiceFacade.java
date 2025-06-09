package za.ac.up.artefactup.service.impl;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.MuseumDTO;
import za.ac.up.artefactup.dto.mapper.MuseumMapper;
import za.ac.up.artefactup.entity.Museum;
import za.ac.up.artefactup.service.MuseumService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MuseumServiceFacade implements MuseumService<MuseumDTO> {

  private final MuseumService<Museum> service;

  private final MuseumMapper mapper;

  @Override
  public List<MuseumDTO> findAll() {
    return mapper.toDTOs(service.findAll());
  }

  @Override
  @Transactional
  public MuseumDTO create(MuseumDTO museumDTO) {
    return mapper.toDto(service.create(mapper.toEntity(museumDTO)));
  }

  @Override
  @Transactional
  public MuseumDTO update(MuseumDTO museumDTO) {
    return mapper.toDto(service.update(mapper.toEntity(museumDTO)));
  }

  @Override
  public Optional<MuseumDTO> findByName(String name) {
    return service.findByName(name).map(mapper::toDto);
  }
}
