package za.ac.up.artefactup.dto.mapper;

import org.mapstruct.Mapper;

import za.ac.up.artefactup.dto.CollectionDTO;
import za.ac.up.artefactup.entity.Collection;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionMapper {

    CollectionDTO toDTO(Collection collection);

    List<CollectionDTO> toDTOs(List<Collection> collections);

    Collection toEntity(CollectionDTO collectionDTO);
}
