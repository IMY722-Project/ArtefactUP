package za.ac.up.artifactup.service;

import za.ac.up.artifactup.entity.Collection;

import java.util.Optional;

public interface CollectionService {

  public Collection saveCollection(Collection collection);

  public Optional<Collection> findByName(String name);

}
