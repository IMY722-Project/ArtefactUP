package za.ac.up.artefactup.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import za.ac.up.artefactup.dto.ScavengerHuntStepDTO;
import za.ac.up.artefactup.dto.StepValidationResultDTO;
import za.ac.up.artefactup.dto.mapper.ScavengerHuntMapper;
import za.ac.up.artefactup.dto.mapper.ScavengerHuntStepMapper;
import za.ac.up.artefactup.entity.ScavengerHunt;
import za.ac.up.artefactup.entity.ScavengerHuntStep;
import za.ac.up.artefactup.entity.UserHuntProgress;
import za.ac.up.artefactup.repository.ScavengerHuntStepRepository;
import za.ac.up.artefactup.service.ScavengerHuntService;
import za.ac.up.artefactup.service.ScavengerHuntStepService;
import za.ac.up.artefactup.service.UserHuntProgressService;
import za.ac.up.artefactup.service.exceptions.NotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ScavengerHuntStepServiceImpl implements ScavengerHuntStepService<ScavengerHuntStep> {

    private final ScavengerHuntStepRepository scavengerHuntStepRepository;
    private final UserHuntProgressService<UserHuntProgress> userHuntProgressService;
    private final ScavengerHuntStepMapper scavengerHuntStepMapper;
    private final ImageValidation imageValidation;
    private final ScavengerHuntService<ScavengerHunt> scavengerHuntService;
    private final ScavengerHuntMapper scavengerHuntMapper;

    @Override
    @Transactional
    public Optional<ScavengerHuntStep> findByName(final String name) {
        return scavengerHuntStepRepository.findScavengerHuntStepByHuntName(name);
    }

    @Override
    @Transactional
    public List<ScavengerHuntStep> findAllStepsByScavengerHuntId(final long id) {
        return scavengerHuntStepRepository.findByHuntIdOrderByStepNumberAsc(id);
    }

    @Override
    @Transactional
    public StepValidationResultDTO validateStep(final String sessionId, final Long huntId, final MultipartFile image) {

        log.info("ValidateStep | sessionId={} huntId={} file={}", sessionId, huntId, image.getOriginalFilename());

        UserHuntProgress userHuntProgress = getUserProgress(huntId, sessionId);

        Optional<ScavengerHunt> optionalScavengerHunt = scavengerHuntService.findById(huntId);

        if (optionalScavengerHunt.isEmpty()) {
            log.error("Scavenger Hunt not found | sessionId={} huntId={}", sessionId, huntId);
            return new StepValidationResultDTO(false, false, null, "Scavenger Hunt not found.", null);
        }

        ScavengerHunt scavengerHunt = optionalScavengerHunt.get();

        if (userHuntProgress.isCompleted()) {
            log.info("Hunt already completed | sessionId={} huntId={}", sessionId, huntId);
            return new StepValidationResultDTO(true, true, null, "Congratulations! You have completed the hunt!",
                    scavengerHuntMapper.toDto(scavengerHunt));
        }

        ScavengerHuntStep scavengerHuntStep = getScavengerHuntStep(huntId, userHuntProgress.getCurrentStep());

        boolean isValidMatch = imageValidation.validateImage(scavengerHuntStep.getArtefact(), image);

        if (isValidMatch) {
            return processStepValidationResult(scavengerHuntStep, userHuntProgress);
        } else {
            log.info("Incorrect image | sessionId={} huntId={} file={}", sessionId, huntId,
                    image.getOriginalFilename());
            return new StepValidationResultDTO(false, false, scavengerHuntStepMapper.toDto(scavengerHuntStep),
                    "Incorrect image. Please try again.", scavengerHuntMapper.toDto(scavengerHunt));
        }

    }

    private ScavengerHuntStepDTO getNextHuntStep(ScavengerHuntStep scavengerHuntStep) {
        return scavengerHuntStepRepository.findByHuntIdOrderByStepNumberAsc(scavengerHuntStep.getHunt().getId())
                .stream().filter(step -> step.getStepNumber() == scavengerHuntStep.getStepNumber() + 1).findFirst()
                .map(scavengerHuntStepMapper::toDto).orElseThrow(() -> {
                    log.error("Next step not found for hunt {}", scavengerHuntStep.getHunt().getId());
                    return new NotFoundException(
                            String.format("Next step not found for hunt %s", scavengerHuntStep.getHunt().getId()));
                });
    }

    private UserHuntProgress getUserProgress(Long huntId, String sessionId) {
        return Optional.ofNullable(userHuntProgressService.getUserProgress(huntId, sessionId)).orElseThrow(() -> {
            log.warn("Progress not found | sessionId={} huntId={}", sessionId, huntId);
            return new NotFoundException(
                    String.format("Progress not found | sessionId=%s huntId=%s", sessionId, huntId));
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

    @Override
    @Transactional
    public StepValidationResultDTO revealStep(final Long huntId, final String sessionId) {
        UserHuntProgress userHuntProgress = getUserProgress(huntId, sessionId);
        ScavengerHuntStep scavengerHuntStep = getScavengerHuntStep(huntId, userHuntProgress.getCurrentStep());

        return processStepValidationResult(scavengerHuntStep, userHuntProgress);
    }

    private StepValidationResultDTO processStepValidationResult(ScavengerHuntStep scavengerHuntStep,
            UserHuntProgress userHuntProgress) {

        if (userHuntProgress.getCurrentStep() + 1 > scavengerHuntStep.getHunt().getSteps().size()) {
            userHuntProgress.setCompleted(true);
            userHuntProgressService.saveUserProgress(userHuntProgress);
            return new StepValidationResultDTO(true, true, null, "Congratulations! You have completed the hunt!",
                    scavengerHuntMapper.toDto(scavengerHuntStep.getHunt()));
        } else {
            userHuntProgress.setCurrentStep(userHuntProgress.getCurrentStep() + 1);
            log.info("UserHuntProgress updated | sessionId={} huntId={} currentStep={}",
                    userHuntProgress.getSessionId(), scavengerHuntStep.getHunt().getId(),
                    userHuntProgress.getCurrentStep());
            userHuntProgressService.saveUserProgress(userHuntProgress);
        }

        return new StepValidationResultDTO(true, false, getNextHuntStep(scavengerHuntStep),
                "Correct! Here is your next clue!", scavengerHuntMapper.toDto(scavengerHuntStep.getHunt()));
    }

}
