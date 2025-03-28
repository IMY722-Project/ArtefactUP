package za.ac.up.artifactup.dto.mappers;


import org.mapstruct.*;
import org.springframework.beans.factory.annotation.*;
import za.ac.up.artifactup.dto.*;
import za.ac.up.artifactup.entity.*;

import java.util.stream.*;

@Mapper(componentModel = "spring", uses = ArtefactMapper.class)
public abstract class MuseumMapper {

    @Autowired
    protected ArtefactMapper artefactMapper;

    @Autowired
    protected OpeningHoursMapper openingHoursMapper;

    @Mapping(target = "artefacts", source = "artefacts")
    @Mapping(target = "openingHours", source = "openingHours")
    public abstract MuseumDTO toDto(Museum museum);


    // Convert DTO back to Museum entity (you might want to handle artefacts and opening hours manually)
    @Mapping(target = "artefacts", ignore = true)
    @Mapping(target = "openingHours", ignore = true)
    public abstract Museum toEntity(MuseumDTO museumDTO);

    @AfterMapping
    protected void mapArtefacts(@MappingTarget Museum museum, MuseumDTO museumDTO) {
        if (museumDTO.getArtefacts() != null) {
            museum.setArtefacts(museumDTO.getArtefacts().stream().map(artefactMapper::toEntity).collect(Collectors.toList()));
        }
    }

    @AfterMapping
    protected void mapOpeningHours(@MappingTarget Museum museum, MuseumDTO museumDTO) {
        if (museumDTO.getOpeningHours() != null) {
            museum.setOpeningHours(museumDTO.getOpeningHours().stream().map(openingHoursMapper::toEntity).collect(Collectors.toList()));
        }
    }


}
