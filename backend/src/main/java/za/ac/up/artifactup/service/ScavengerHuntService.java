package za.ac.up.artifactup.service;

import java.util.List;
import java.util.Optional;

public interface ScavengerHuntService<T> {

    List<T> findAll();

    T create(T hunt);

    void deleteById(Long id);

    Optional<T> findByName(String name);

    Optional<T> findById(Long id);

    int count();
}

