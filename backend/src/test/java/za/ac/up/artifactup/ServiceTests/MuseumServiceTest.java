package za.ac.up.artefactup.service;

import za.ac.up.artefactup.entity.Museum;
import za.ac.up.artefactup.repository.MuseumRepository;
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
public class MuseumServiceTest {

    @Mock
    private MuseumRepository museumRepository;

    @InjectMocks
    private MuseumService museumService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Museum entity = new Museum();
        entity.setId(1L);
        when(museumRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = museumService.getMuseumById(1L);
        assertEquals(1L, result.getId());
    }
}
