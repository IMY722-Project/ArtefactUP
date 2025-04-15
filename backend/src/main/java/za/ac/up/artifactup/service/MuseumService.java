package za.ac.up.artifactup.service;

import java.util.List;
import java.util.Optional;

public interface MuseumService<T> {

  List<T> findAll();

  T create(T museum);

  T update(T museum);

  Optional<T> findByName(String name);
}
