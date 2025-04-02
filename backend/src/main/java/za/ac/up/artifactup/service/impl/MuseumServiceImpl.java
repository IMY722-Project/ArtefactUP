package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.entity.Museum;
import za.ac.up.artifactup.repository.MuseumRepository;
import za.ac.up.artifactup.service.MuseumService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MuseumServiceImpl implements MuseumService<Museum> {

  private final MuseumRepository repository;

  public List<Museum> findAll() {
    return repository.findAll();
  }

  @Override
  @Transactional
  public Museum create(Museum museum) {
    return repository.save(museum);
  }

  @Override
  @Transactional
  public Museum update(Museum museum) {
    return create(museum);
  }

  @Override
  public Optional<Museum> findByName(String name) {
    return repository.findByName(name);
  }
}
