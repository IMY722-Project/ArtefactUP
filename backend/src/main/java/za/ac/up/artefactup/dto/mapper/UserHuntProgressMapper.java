package za.ac.up.artefactup.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import za.ac.up.artefactup.dto.UserHuntProgressDTO;
import za.ac.up.artefactup.dto.qualifier.ScavengerHuntQualifier;
import za.ac.up.artefactup.dto.qualifier.ScavengerHuntStepQualifier;
import za.ac.up.artefactup.entity.UserHuntProgress;

@Mapper(componentModel = "spring", uses = { ScavengerHuntQualifier.class, ScavengerHuntStepMapper.class,
        ScavengerHuntStepQualifier.class })
public interface UserHuntProgressMapper {

    @Mapping(target = "scavengerHuntName", source = "hunt.name")
    @Mapping(target = "currentStep", source = "userHuntProgress", qualifiedByName = "intToScavengerHuntStep")
    UserHuntProgressDTO toDto(UserHuntProgress userHuntProgress);

    @Mapping(target = "hunt", source = "scavengerHuntName", qualifiedByName = "stringToScavengerHunt")
    @Mapping(target = "currentStep", source = "currentStep.stepNumber")
    UserHuntProgress toEntity(UserHuntProgressDTO userHuntProgressDTO);

    List<UserHuntProgressDTO> toDtoList(List<UserHuntProgress> userHuntProgresses);

    List<UserHuntProgress> toEntityList(List<UserHuntProgressDTO> userHuntProgressDTOs);
}
