package za.ac.up.artefactup.aspect;

import lombok.extern.slf4j.Slf4j;
import za.ac.up.artefactup.service.exceptions.ValidationException;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Aspect
@Component
@Slf4j
public class ValidationAspectHuntStep {

    @Before(value = "execution(* za.ac.up.artifactup.service.impl.ScavengerHuntStepServiceImpl.validateStep(..)) && args(sessionId, huntId, image)", argNames = "sessionId,huntId,image")
    public void validateInputs(String sessionId, Long huntId, MultipartFile image) {
        if (sessionId == null || sessionId.isEmpty()) {
            log.error("Validation failed: sessionId is null or empty");
            throw new ValidationException("Session ID cannot be null or empty");
        }

        if (huntId == null || huntId <= 0) {
            log.error("Validation failed: huntId is null or invalid");
            throw new ValidationException("Hunt ID must be a positive number");
        }

        if (image == null || image.isEmpty()) {
            log.error("Validation failed: image file is null or empty");
            throw new ValidationException("Image file cannot be null or empty");
        }

        log.info("Validation passed for sessionId={}, huntId={}, image={}", sessionId, huntId,
                image.getOriginalFilename());
    }
}