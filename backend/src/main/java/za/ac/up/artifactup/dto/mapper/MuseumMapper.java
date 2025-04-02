package za.ac.up.artifactup.dto.mapper;


import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;
import za.ac.up.artifactup.dto.*;
import za.ac.up.artifactup.entity.*;

import java.util.List;
import java.util.stream.*;

@Mapper(componentModel = "spring", uses = {ArtefactMapper.class, OpeningHoursMapper.class})
public interface MuseumMapper {

    @Mapping(target = "openingHours", source = "openingHours")
    MuseumDTO toDto(Museum museum);

    List<MuseumDTO> toDTOs(List<Museum> artefacts);

    List<Museum> toEntities(List<MuseumDTO> dtos);

    Museum toEntity(MuseumDTO museumDTO);

    //    @AfterMapping
    //    protected void mapArtefacts(@MappingTarget Museum museum, MuseumDTO museumDTO) {
    //        if (museumDTO.getArtefacts() != null) {
    //            museum.setArtefacts(museumDTO.getArtefacts().stream().map(artefactMapper::toEntity).collect(Collectors.toList()));
    //        }
    //    }
    //
    //    @AfterMapping
    //    protected void mapOpeningHours(@MappingTarget Museum museum, MuseumDTO museumDTO) {
    //        if (museumDTO.getOpeningHours() != null) {
    //            museum.setOpeningHours(museumDTO.getOpeningHours().stream().map(openingHoursMapper::toEntity).collect(Collectors.toList()));
    //        }
    //    }
}
