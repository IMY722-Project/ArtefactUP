package za.ac.up.artifactup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.up.artifactup.entity.Collection;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Collection, Long> {

  Optional<Collection> findByName(String name);
}
