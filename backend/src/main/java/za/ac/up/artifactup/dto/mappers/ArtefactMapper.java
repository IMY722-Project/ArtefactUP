package za.ac.up.artifactup.dto.mappers;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;
import za.ac.up.artifactup.dto.*;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.entity.*;
import za.ac.up.artifactup.service.*;

import java.util.*;

/*
 * TODO:
 *  1. Create Museum service and uncomment below museum implementation*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ArtefactMapper {

    @Autowired
    protected CollectionService collectionService;

//    @Autowired
//    protected MuseumService museumService;

    @Mapping(target = "collectionName", expression = "java(artefact.getCollection().getName())")
    @Mapping(target = "museumName", expression = "java(artefact.getMuseum().getName())")
    public abstract ArtefactDTO toDTO(Artefact artefact);

    public abstract List<ArtefactDTO> toDTOs(List<Artefact> artefacts);

    @Mapping(target = "collection", ignore = true)
    @Mapping(target = "museum", ignore = true)
    public abstract Artefact toEntity(ArtefactDTO artefactDTO);

    @AfterMapping
    protected void mapCollection(@MappingTarget Artefact artefact, ArtefactDTO artefactDTO) {
        if (artefactDTO.getCollectionName() != null) {
            Collection collection = collectionService.findByName(artefactDTO.getCollectionName()).orElse(null);
            artefact.setCollection(collection);
        }
    }

//    @AfterMapping
//    protected void mapMuseum(@MappingTarget Artefact artefact, ArtefactDTO artefactDTO) {
//        if (artefactDTO.getMuseumName() != null) {
//           Museum museum = museumService.findByName(artefactDTO.getMuseumName()).orElse(null);
//            artefact.setMuseum(museum);
//        }
//    }

}

