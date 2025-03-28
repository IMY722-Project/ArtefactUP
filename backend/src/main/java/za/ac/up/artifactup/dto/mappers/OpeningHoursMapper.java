package za.ac.up.artifactup.dto.mappers;

import org.mapstruct.*;
import za.ac.up.artifactup.dto.*;
import za.ac.up.artifactup.entity.*;

/*
 *TODO:
 * 1. Uncomment museum mappings*/

@Mapper(componentModel = "spring")
public interface OpeningHoursMapper {

    //    @Autowired
//    private MuseumService museumService;

    @Mapping(target = "museumId", source = "museum.id")
    OpeningHoursDTO toDto(OpeningHours openingHours);

    @Mapping(target = "museum", ignore = true)
    OpeningHours toEntity(OpeningHoursDTO openingHoursDTO);

//    @AfterMapping
//    default void mapMuseum(@MappingTarget OpeningHours openingHours, OpeningHoursDTO openingHoursDTO) {
//        if (openingHoursDTO.getMuseumId() != null) {
//            openingHours.setMuseum(museumService.findById(openingHoursDTO.getMuseumId()));
//        }
//    }
}

