package za.ac.up.artifactup.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.aspect.ValidateInputUserProgress;
import za.ac.up.artifactup.dto.UserHuntStatsDTO;
import za.ac.up.artifactup.entity.ScavengerHunt;
import za.ac.up.artifactup.entity.UserHuntProgress;
import za.ac.up.artifactup.repository.ScavengerHuntStepRepository;
import za.ac.up.artifactup.repository.UserHuntProgressRepository;
import za.ac.up.artifactup.service.ScavengerHuntService;
import za.ac.up.artifactup.service.UserHuntProgressService;
import za.ac.up.artifactup.service.exceptions.NotFoundException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserHuntProgressServiceImpl implements UserHuntProgressService<UserHuntProgress> {

    private final UserHuntProgressRepository userHuntProgressRepository;
    private final ScavengerHuntService<ScavengerHunt> scavengerHuntService;
    private final ScavengerHuntStepRepository scavengerHuntStepRepository;

    @Override
    @ValidateInputUserProgress
    public UserHuntProgress getUserProgress(final Long huntId, final String sessionId) {
        Optional<UserHuntProgress> userHuntProgress = userHuntProgressRepository.findBySessionIdAndHuntId(sessionId, huntId);
        if (userHuntProgress.isPresent()) {
            log.info("UserHuntProgress found for sessionId: {} and huntId: {}", sessionId, huntId);
            return userHuntProgress.get();
        } else {
            log.info("UserHuntProgress not found for sessionId: {} and huntId: {}", sessionId, huntId);
            throw new NotFoundException("UserHuntProgress not found for sessionId: " + sessionId + " and huntId: " + huntId);
        }
    }

    @Override
    @Transactional
    public UserHuntProgress saveUserProgress(final UserHuntProgress userHuntProgress) {
        return userHuntProgressRepository.save(userHuntProgress);
    }

    @Override
    @ValidateInputUserProgress
    @Transactional
    public UserHuntProgress startHunt(final Long huntId, final String sessionId) {

        Optional<UserHuntProgress> userHuntProgress = userHuntProgressRepository.findBySessionIdAndHuntId(sessionId, huntId);
        if (userHuntProgress.isPresent()) {
            log.info("UserHuntProgress already exists");
            return userHuntProgress.get();
        }

        Optional<ScavengerHunt> scavengerHunt = scavengerHuntService.findById(huntId);
        if (scavengerHunt.isEmpty()) {
            log.error("ScavengerHunt not found for id: {}", huntId);
            throw new RuntimeException("ScavengerHunt not found for id: " + huntId);
        }

        log.info("Creating new UserHuntProgress for sessionId: {} and huntId: {}", sessionId, huntId);
        UserHuntProgress newUserHuntProgress = new UserHuntProgress();
        newUserHuntProgress.setSessionId(sessionId);
        newUserHuntProgress.setHunt(scavengerHunt.get());
        newUserHuntProgress.setCurrentStep(1);
        newUserHuntProgress.setCompleted(false);

        return userHuntProgressRepository.save(newUserHuntProgress);
    }

    @Override
    public UserHuntStatsDTO getUserHuntStats(final String sessionId) {
        int attempted = (int) userHuntProgressRepository.countBySessionId(sessionId);
        int completed = (int) userHuntProgressRepository.countBySessionIdAndCompletedTrue(sessionId);
        int artefactsFound = Math.toIntExact(Optional.ofNullable(userHuntProgressRepository.sumOfArtefactsFound(sessionId)).orElse(0L));
        int totalArtefacts = (int) scavengerHuntStepRepository.count();
        int totalHunts = scavengerHuntService.count();
        List<Integer> attemptedHuntIds = userHuntProgressRepository.findAllBySessionId(sessionId).stream().flatMap(Collection::stream)
                .map(userHuntProgress -> userHuntProgress.getHunt().getId().intValue())
                .toList();

        return new UserHuntStatsDTO(attempted, completed, artefactsFound, totalArtefacts, totalHunts, attemptedHuntIds);
    }

}
