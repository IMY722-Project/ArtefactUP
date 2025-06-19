package za.ac.up.artefactup.aspect;

import lombok.extern.slf4j.Slf4j;
import za.ac.up.artefactup.service.exceptions.ValidationException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspectUserProgress {

    @Before("@annotation(za.ac.up.artifactup.aspect.ValidateInputUserProgress)")
    public void validateInputs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length < 2 || args[0] == null || args[1] == null || !(args[0] instanceof final Long huntId)
                || !(args[1] instanceof final String sessionId)) {
            throw new ValidationException("Invalid input: huntId and sessionId must not be null or invalid.");
        }

        if (huntId <= 0 || sessionId.isEmpty()) {
            throw new ValidationException(
                    "Invalid input: huntId must be greater than 0 and sessionId must not be empty.");
        }

        log.info("Validation passed for method: {}", joinPoint.getSignature().getName());
    }
}