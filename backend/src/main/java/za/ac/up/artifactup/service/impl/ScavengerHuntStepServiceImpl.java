package za.ac.up.artifactup.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import za.ac.up.artifactup.dto.ScavengerHuntStepDTO;
import za.ac.up.artifactup.dto.StepValidationResultDTO;
import za.ac.up.artifactup.dto.mapper.ScavengerHuntStepMapper;
import za.ac.up.artifactup.entity.ScavengerHuntStep;
import za.ac.up.artifactup.entity.UserHuntProgress;
import za.ac.up.artifactup.repository.ScavengerHuntStepRepository;
import za.ac.up.artifactup.service.ScavengerHuntStepService;
import za.ac.up.artifactup.service.UserHuntProgressService;
import za.ac.up.artifactup.service.exceptions.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ScavengerHuntStepServiceImpl implements ScavengerHuntStepService<ScavengerHuntStep> {

    private final ScavengerHuntStepRepository scavengerHuntStepRepository;
    private final UserHuntProgressService<UserHuntProgress> userHuntProgressService;
    private final ScavengerHuntStepMapper scavengerHuntStepMapper;
    private final ImageValidation imageValidation;

    @Override
    @Transactional
    public Optional<ScavengerHuntStep> findByName(final String name) {
        return scavengerHuntStepRepository.findScavengerHuntStepByHuntName(name);
    }

    @Override
    public List<ScavengerHuntStep> findAllStepsByScavengerHuntId(final long id) {
        return scavengerHuntStepRepository.findByHuntIdOrderByStepNumberAsc(id);
    }

    @Override
    @Transactional
    public StepValidationResultDTO validateStep(final String sessionId, final Long huntId, final MultipartFile image) {

        log.info("ValidateStep | sessionId={} huntId={} file={}", sessionId, huntId, image.getOriginalFilename());

        UserHuntProgress userHuntProgress = getUserProgress(huntId, sessionId);

        if (userHuntProgress.isCompleted()) {
            log.info("Hunt already completed | sessionId={} huntId={}", sessionId, huntId);
            return new StepValidationResultDTO(true, true, null, "Congratulations! You have completed the hunt!");
        }

        ScavengerHuntStep scavengerHuntStep = getScavengerHuntStep(huntId, userHuntProgress.getCurrentStep());

        boolean isValidMatch = imageValidation.validateImage(scavengerHuntStep.getArtefact(), image);

        if (isValidMatch) {
            if (userHuntProgress.getCurrentStep() + 1 > scavengerHuntStep.getHunt().getSteps().size()) {
                userHuntProgress.setCompleted(true);
                userHuntProgressService.saveUserProgress(userHuntProgress);
                return new StepValidationResultDTO(true, true, null, "Congratulations! You have completed the hunt!");
            } else {
                userHuntProgress.setCurrentStep(userHuntProgress.getCurrentStep() + 1);
                log.info(String.format("UserHuntProgress updated | sessionId=%s huntId=%s currentStep=%d", sessionId, huntId, userHuntProgress.getCurrentStep()));
                userHuntProgressService.saveUserProgress(userHuntProgress);
            }

            return new StepValidationResultDTO(true, false, getNextHuntStep(scavengerHuntStep), "Correct! Here is your next clue!");
        } else {
            log.info("Incorrect image | sessionId={} huntId={} file={}", sessionId, huntId, image.getOriginalFilename());
            return new StepValidationResultDTO(false, false, scavengerHuntStepMapper.toDto(scavengerHuntStep), "Incorrect image. Please try again.");
        }

    }

    private ScavengerHuntStepDTO getNextHuntStep(ScavengerHuntStep scavengerHuntStep) {
        return scavengerHuntStepRepository.findByHuntIdOrderByStepNumberAsc(scavengerHuntStep.getHunt().getId()).stream().filter(step -> step.getStepNumber() == scavengerHuntStep.getStepNumber() + 1).findFirst().map(scavengerHuntStepMapper::toDto).orElseThrow(() -> {
            log.error("Next step not found for hunt {}", scavengerHuntStep.getHunt().getId());
            return new NotFoundException(String.format("Next step not found for hunt %s", scavengerHuntStep.getHunt().getId()));
        });
    }

    private UserHuntProgress getUserProgress(Long huntId, String sessionId) {
        return Optional.ofNullable(userHuntProgressService.getUserProgress(huntId, sessionId)).orElseThrow(() -> {
            log.warn("Progress not found | sessionId={} huntId={}", sessionId, huntId);
            return new NotFoundException(String.format("Progress not found | sessionId=%s huntId=%s", sessionId, huntId));
        });
    }

    @Override
    @Transactional
    public ScavengerHuntStep getScavengerHuntStep(Long huntId, int currentStep) {
        return scavengerHuntStepRepository.findByHuntIdAndStepNumber(huntId, currentStep).orElseThrow(() -> {
            log.error("Step {} missing for hunt {}", currentStep, huntId);
            return new NotFoundException(String.format("Step %s missing for hunt %s", currentStep, huntId));
        });
    }
}
