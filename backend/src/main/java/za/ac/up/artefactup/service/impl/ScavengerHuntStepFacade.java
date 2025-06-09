package za.ac.up.artefactup.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.ScavengerHuntStepDTO;
import za.ac.up.artefactup.dto.StepValidationResultDTO;
import za.ac.up.artefactup.dto.mapper.ScavengerHuntStepMapper;
import za.ac.up.artefactup.entity.ScavengerHuntStep;
import za.ac.up.artefactup.service.ScavengerHuntStepService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScavengerHuntStepFacade implements ScavengerHuntStepService<ScavengerHuntStepDTO> {

    private final ScavengerHuntStepMapper scavengerHuntStepMapper;
    private final ScavengerHuntStepService<ScavengerHuntStep> scavengerHuntStepService;

    @Override
    @Transactional
    public Optional<ScavengerHuntStepDTO> findByName(final String name) {
        return scavengerHuntStepService.findByName(name).map(scavengerHuntStepMapper::toDto);
    }

    @Override
    @Transactional
    public List<ScavengerHuntStepDTO> findAllStepsByScavengerHuntId(final long id) {
        return scavengerHuntStepService.findAllStepsByScavengerHuntId(id).stream().map(scavengerHuntStepMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StepValidationResultDTO validateStep(final String sessionId, final Long huntId, final MultipartFile image) {
        return scavengerHuntStepService.validateStep(sessionId, huntId, image);
    }

    @Override
    @Transactional
    public ScavengerHuntStepDTO getScavengerHuntStep(final Long huntId, final int currentStep) {
        return scavengerHuntStepMapper.toDto(scavengerHuntStepService.getScavengerHuntStep(huntId, currentStep));
    }

    @Override
    @Transactional
    public StepValidationResultDTO revealStep(final Long huntId, final String sessionId) {
        return scavengerHuntStepService.revealStep(huntId, sessionId);
    }

}
