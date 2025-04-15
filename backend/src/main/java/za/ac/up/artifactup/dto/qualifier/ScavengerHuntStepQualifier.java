package za.ac.up.artifactup.dto.qualifier;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import za.ac.up.artifactup.dto.ScavengerHuntStepDTO;
import za.ac.up.artifactup.entity.UserHuntProgress;
import za.ac.up.artifactup.service.ScavengerHuntStepService;

@Component
@RequiredArgsConstructor
public class ScavengerHuntStepQualifier {

    private final ScavengerHuntStepService<ScavengerHuntStepDTO> scavengerHuntStepService;

    @Named("intToScavengerHuntStep")
    public ScavengerHuntStepDTO intToScavengerHuntStep(UserHuntProgress userHuntProgress) {
        return scavengerHuntStepService.getScavengerHuntStep(userHuntProgress.getHunt().getId(), userHuntProgress.getCurrentStep());
    }

}
