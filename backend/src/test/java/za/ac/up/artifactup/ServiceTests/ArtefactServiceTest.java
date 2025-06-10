package za.ac.up.artefactup.service;

import za.ac.up.artefactup.entity.Artefact;
import za.ac.up.artefactup.repository.ArtefactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ArtefactServiceTest {

    @Mock
    private ArtefactRepository artefactRepository;

    @InjectMocks
    private ArtefactService artefactService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetArtefactById() {
        Artefact a = new Artefact();
        a.setId(1L);
        a.setName("Test");
        when(artefactRepository.findById(1L)).thenReturn(Optional.of(a));

        Artefact result = artefactService.getArtefactById(1L);
        assertEquals("Test", result.getName());
    }
}
