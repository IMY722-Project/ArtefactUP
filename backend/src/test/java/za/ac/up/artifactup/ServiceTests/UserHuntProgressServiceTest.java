package za.ac.up.artefactup.service;

import za.ac.up.artefactup.entity.UserHuntProgress;
import za.ac.up.artefactup.repository.UserHuntProgressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserHuntProgressServiceTest {

    @Mock
    private UserHuntProgressRepository userHuntProgressRepository;

    @InjectMocks
    private UserHuntProgressService userHuntProgressService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        UserHuntProgress entity = new UserHuntProgress();
        entity.setId(1L);
        when(userHuntProgressRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = userHuntProgressService.getUserHuntProgressById(1L);
        assertEquals(1L, result.getId());
    }
}
