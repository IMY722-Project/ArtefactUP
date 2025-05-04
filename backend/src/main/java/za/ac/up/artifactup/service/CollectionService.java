package za.ac.up.artifactup.service;

import java.util.Optional;

public interface CollectionService<T> {

  public T saveCollection(T collection);

  public Optional<T> findByName(String name);

}
