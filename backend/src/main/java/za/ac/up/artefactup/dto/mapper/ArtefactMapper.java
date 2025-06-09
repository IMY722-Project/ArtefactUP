package za.ac.up.artefactup.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import za.ac.up.artefactup.dto.ArtefactDTO;
import za.ac.up.artefactup.dto.qualifier.ArtefactQualifier;
import za.ac.up.artefactup.dto.qualifier.MuseumQualifier;
import za.ac.up.artefactup.entity.Artefact;
import za.ac.up.artefactup.entity.Collection;

import java.util.List;

@Mapper(componentModel = "spring", uses = { MuseumQualifier.class, ArtefactQualifier.class })
public interface ArtefactMapper {

  @Mapping(target = "type", expression = "java(artefact.getCollection().getName())")
  @Mapping(target = "museumName", source = "museum.name")
  @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "mapToURL")
  ArtefactDTO toDTO(Artefact artefact);

  List<ArtefactDTO> toDTOs(List<Artefact> artefacts);

  @Mapping(target = "collection", source = "type")
  @Mapping(target = "museum", source = "museumName", qualifiedByName = "stringToMuseum")
  @Mapping(target = "imageUrl", source = "imageFile", qualifiedByName = "fileToUrl")
  Artefact toEntity(ArtefactDTO artefactDTO);

  default Collection mapCollection(String value) {
    return new Collection().builder().name(value).description("").build();
  }
}
