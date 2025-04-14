package za.ac.up.artifactup.service.impl;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.entity.UserHuntProgress;
import za.ac.up.artifactup.repository.UserHuntProgressRepository;
import za.ac.up.artifactup.service.UserHuntProgressService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserHuntProgressServiceImpl implements UserHuntProgressService<UserHuntProgress> {

    private final UserHuntProgressRepository userHuntProgressRepository;

    @Override
    public Optional<UserHuntProgress> getUserProgress(final Long huntId, final String sessionId) {
        return userHuntProgressRepository.findBySessionUserIdAndHuntId(sessionId, huntId);
    }

    @Override
    public UserHuntProgress saveUserProgress(final UserHuntProgress userHuntProgress) {
        return userHuntProgressRepository.save(userHuntProgress);
    }

}
