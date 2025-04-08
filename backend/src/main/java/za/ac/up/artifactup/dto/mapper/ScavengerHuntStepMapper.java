package za.ac.up.artifactup.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import za.ac.up.artifactup.dto.ScavengerHuntStepDTO;
import za.ac.up.artifactup.dto.qualifier.ArtefactQualifier;
import za.ac.up.artifactup.dto.qualifier.MuseumQualifier;
import za.ac.up.artifactup.dto.qualifier.ScavengerHuntQualifier;
import za.ac.up.artifactup.entity.ScavengerHuntStep;

@Mapper(componentModel = "spring", uses = {MuseumQualifier.class, ArtefactQualifier.class, ScavengerHuntQualifier.class})
public interface ScavengerHuntStepMapper {

    @Mapping(target = "scavengerHuntName", source = "hunt.name")
    @Mapping(target = "museumName", source = "museum.name")
    @Mapping(target = "artefactName", source = "artefact.title")
    ScavengerHuntStepDTO toDto(ScavengerHuntStep scavengerHuntStep);

    @Mapping(target = "artefact", source = "artefactName", qualifiedByName = "stringToArtefact")
    @Mapping(target = "museum", source = "museumName", qualifiedByName = "stringToMuseum")
    @Mapping(target = "hunt", source = "scavengerHuntName", qualifiedByName = "stringToScavengerHunt")
    ScavengerHuntStep toEntity(ScavengerHuntStepDTO scavengerHuntStepDTO);

    List<ScavengerHuntStepDTO> toDtoList(List<ScavengerHuntStep> scavengerHuntSteps);

    List<ScavengerHuntStep> toEntityList(List<ScavengerHuntStepDTO> scavengerHuntStepDTOs);
}
