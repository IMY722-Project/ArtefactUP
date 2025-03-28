package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.repository.CategoryRepository;
import za.ac.up.artifactup.service.CollectionService;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

  private final CategoryRepository categoryRepository;

  @Override
  public Collection saveCollection(Collection collection) {
    return categoryRepository.save(collection);
  }

  @Override
  public Optional<Collection> findByName(String name) {
    return categoryRepository.findByName(name);
  }
}
