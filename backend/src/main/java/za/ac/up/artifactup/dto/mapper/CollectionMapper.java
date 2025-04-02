package za.ac.up.artifactup.dto.mapper;

import org.mapstruct.Mapper;
import za.ac.up.artifactup.dto.CollectionDTO;
import za.ac.up.artifactup.entity.Collection;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CollectionMapper {

    CollectionDTO toDTO(Collection collection);

    List<CollectionDTO> toDTOs(List<Collection> collections);

    Collection toEntity(CollectionDTO collectionDTO);
}
