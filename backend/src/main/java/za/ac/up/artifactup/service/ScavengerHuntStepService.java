package za.ac.up.artifactup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;
import za.ac.up.artifactup.dto.StepValidationResultDTO;

public interface ScavengerHuntStepService<T> {

    Optional<T> findByName(String name);

    List<T> findAllStepsByScavengerHuntId(long id);

    StepValidationResultDTO validateStep(String sessionId, Long huntId, MultipartFile image);

    T getScavengerHuntStep(Long huntId, int currentStep);

    StepValidationResultDTO revealStep(Long huntId, String sessionId);
}
