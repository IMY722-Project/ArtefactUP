package za.ac.up.artifactup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.FileStorage;
import za.ac.up.artifactup.entity.FileType;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {

    Optional<FileStorage> findFirstByArtefactAndFileType(Artefact artefact, FileType fileType);
}

