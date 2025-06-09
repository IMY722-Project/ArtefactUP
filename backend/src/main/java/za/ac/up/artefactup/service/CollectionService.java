package za.ac.up.artefactup.service;

import java.util.Optional;

public interface CollectionService<T> {

  public T saveCollection(T collection);

  public Optional<T> findByName(String name);

}
