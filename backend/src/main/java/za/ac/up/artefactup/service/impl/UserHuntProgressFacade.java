package za.ac.up.artefactup.service.impl;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.dto.UserHuntProgressDTO;
import za.ac.up.artefactup.dto.UserHuntStatsDTO;
import za.ac.up.artefactup.dto.mapper.UserHuntProgressMapper;
import za.ac.up.artefactup.entity.UserHuntProgress;
import za.ac.up.artefactup.service.UserHuntProgressService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserHuntProgressFacade implements UserHuntProgressService<UserHuntProgressDTO> {

    private final UserHuntProgressService<UserHuntProgress> userHuntProgressService;
    private final UserHuntProgressMapper userHuntProgressMapper;

    @Override
    @Transactional
    public UserHuntProgressDTO getUserProgress(Long huntId, String sessionId) {
        return Optional.ofNullable(userHuntProgressService.getUserProgress(huntId, sessionId))
                .map(userHuntProgressMapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public UserHuntProgressDTO saveUserProgress(final UserHuntProgressDTO userHuntProgress) {
        return Optional
                .ofNullable(userHuntProgressService.saveUserProgress(userHuntProgressMapper.toEntity(userHuntProgress)))
                .map(userHuntProgressMapper::toDto).orElse(null);
    }

    @Override
    @Transactional
    public UserHuntProgressDTO startHunt(Long huntId, String sessionId) {
        return Optional.ofNullable(userHuntProgressService.startHunt(huntId, sessionId))
                .map(userHuntProgressMapper::toDto).orElse(null);
    }

    @Override
    public UserHuntStatsDTO getUserHuntStats(final String sessionId) {
        return userHuntProgressService.getUserHuntStats(sessionId);
    }
}
