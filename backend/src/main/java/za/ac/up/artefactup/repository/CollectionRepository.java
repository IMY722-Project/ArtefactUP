package za.ac.up.artefactup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.up.artefactup.entity.Collection;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

  Optional<Collection> findByName(String name);
}
