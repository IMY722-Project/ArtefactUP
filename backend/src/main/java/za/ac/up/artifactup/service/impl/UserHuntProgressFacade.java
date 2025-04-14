package za.ac.up.artifactup.service.impl;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.dto.UserHuntProgressDTO;
import za.ac.up.artifactup.dto.mapper.UserHuntProgressMapper;
import za.ac.up.artifactup.entity.UserHuntProgress;
import za.ac.up.artifactup.service.UserHuntProgressService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserHuntProgressFacade implements UserHuntProgressService<UserHuntProgressDTO> {

    private final UserHuntProgressService<UserHuntProgress> userHuntProgressService;
    private final UserHuntProgressMapper userHuntProgressMapper;

    @Override
    @Transactional
    public Optional<UserHuntProgressDTO> getUserProgress(Long huntId, String sessionId) {
        return userHuntProgressService.getUserProgress(huntId, sessionId).map(userHuntProgressMapper::toDto);
    }

    @Override
    public UserHuntProgressDTO saveUserProgress(final UserHuntProgressDTO userHuntProgress) {
        return Optional.ofNullable(userHuntProgressService.saveUserProgress(userHuntProgressMapper.toEntity(userHuntProgress))).map(userHuntProgressMapper::toDto).orElse(null);
    }
}
