package za.ac.up.artefactup.service;

import za.ac.up.artefactup.entity.ScavengerHunt;
import za.ac.up.artefactup.repository.ScavengerHuntRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ScavengerHuntServiceTest {

    @Mock
    private ScavengerHuntRepository scavengerHuntRepository;

    @InjectMocks
    private ScavengerHuntService scavengerHuntService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        ScavengerHunt entity = new ScavengerHunt();
        entity.setId(1L);
        when(scavengerHuntRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = scavengerHuntService.getScavengerHuntById(1L);
        assertEquals(1L, result.getId());
    }
}
