package za.ac.up.artifactup.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import za.ac.up.artifactup.dto.ScavengerHuntDTO;
import za.ac.up.artifactup.entity.ScavengerHunt;

@Mapper(componentModel = "spring", uses = {ScavengerHuntStepMapper.class})
public interface ScavengerHuntMapper {

    @Mapping(target = "steps", source = "steps")
    ScavengerHuntDTO toDto(ScavengerHunt scavengerHunt);

    @Mapping(target = "steps", source = "steps")
    ScavengerHunt toEntity(ScavengerHuntDTO scavengerHuntDto);

    @Mapping(target = "steps", source = "steps")
    List<ScavengerHuntDTO> toDtoList(List<ScavengerHunt> scavengerHunts);

    @Mapping(target = "steps", source = "steps")
    List<ScavengerHunt> toEntityList(List<ScavengerHuntDTO> scavengerHuntDtos);
}
