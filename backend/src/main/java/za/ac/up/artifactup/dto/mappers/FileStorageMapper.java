package za.ac.up.artifactup.dto.mappers;

import org.mapstruct.*;
import za.ac.up.artifactup.dto.*;
import za.ac.up.artifactup.entity.*;

@Mapper(componentModel = "spring")
public interface FileStorageMapper {

    @Mapping(source = "artefact.id", target = "artefactId")
    FileStorageDTO toDto(FileStorage fileStorage);

    @Mapping(source = "artefactId", target = "artefact.id")
    FileStorage toEntity(FileStorageDTO fileStorageDTO);

}
