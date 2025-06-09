package za.ac.up.artefactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.ScavengerHuntStepDTO;
import za.ac.up.artefactup.entity.UserHuntProgress;
import za.ac.up.artefactup.service.ScavengerHuntStepService;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScavengerHuntStepQualifier {

    private final ScavengerHuntStepService<ScavengerHuntStepDTO> scavengerHuntStepService;

    @Named("intToScavengerHuntStep")
    public ScavengerHuntStepDTO intToScavengerHuntStep(UserHuntProgress userHuntProgress) {
        return scavengerHuntStepService.getScavengerHuntStep(userHuntProgress.getHunt().getId(),
                userHuntProgress.getCurrentStep());
    }

}
