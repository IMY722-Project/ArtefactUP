package za.ac.up.artifactup.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import za.ac.up.artifactup.dto.ScavengerHuntStepDTO;
import za.ac.up.artifactup.dto.StepValidationResultDTO;
import za.ac.up.artifactup.dto.mapper.ScavengerHuntStepMapper;
import za.ac.up.artifactup.entity.ScavengerHuntStep;
import za.ac.up.artifactup.service.ScavengerHuntStepService;

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
        return scavengerHuntStepService.findAllStepsByScavengerHuntId(id).stream().map(scavengerHuntStepMapper::toDto).collect(Collectors.toList());
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

}

