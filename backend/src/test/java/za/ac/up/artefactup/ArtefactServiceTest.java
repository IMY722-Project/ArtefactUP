package za.ac.up.artefactup;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.up.artefactup.entity.Artefact;
import za.ac.up.artefactup.repository.ArtefactRepository;
import za.ac.up.artefactup.service.impl.ArtefactServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArtefactServiceTest {

    @Mock
    private ArtefactRepository artefactRepository;

    @InjectMocks
    private ArtefactServiceImpl artefactService;

    private Artefact mockArtefact;

    @BeforeEach
    public void setUp() {
        mockArtefact = new Artefact();
        mockArtefact.setId(1L);
        mockArtefact.setTitle("Ancient Vase");
        mockArtefact.setCreator("Unknown");
        mockArtefact.setDescription("A vase from 500 BC");
    }

    @Test
    public void testFindByTitle() {
        when(artefactRepository.findByTitle("Ancient Vase")).thenReturn(Optional.of(mockArtefact));

        Optional<Artefact> result = artefactService.findByTitle("Ancient Vase");

        assertTrue(result.isPresent());
        assertEquals("Ancient Vase", result.get().getTitle());
        assertEquals("Unknown", result.get().getCreator());
    }
}
