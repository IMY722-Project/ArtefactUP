package za.ac.up.artefactup.service;

import za.ac.up.artefactup.entity.ScavengerHuntStep;
import za.ac.up.artefactup.repository.ScavengerHuntStepRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScavengerHuntStepServiceTest {

    @Mock
    private ScavengerHuntStepRepository scavengerHuntStepRepository;

    @InjectMocks
    private ScavengerHuntStepService scavengerHuntStepService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        ScavengerHuntStep entity = new ScavengerHuntStep();
        entity.setId(1L);
        when(scavengerHuntStepRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = scavengerHuntStepService.getScavengerHuntStepById(1L);
        assertEquals(1L, result.getId());
    }
}
