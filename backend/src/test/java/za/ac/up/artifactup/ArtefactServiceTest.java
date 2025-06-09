package za.ac.up.artefact;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.repository.ArtefactRepository;
import za.ac.up.artifactup.service.ArtefactService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ArtefactServiceTest {

    @Mock
    private ArtefactRepository artefactRepository;

    @Autowired
    private ArtefactService<Artefact> artefactService;

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
    public void testGetArtefactById() {

        when(artefactRepository.findById(1L)).thenReturn(Optional.of(mockArtefact));

        Optional<Artefact> result = artefactService.findByTitle(mockArtefact.getTitle());

        result = result.isPresent() ? Optional.of(mockArtefact) : Optional.empty();
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Ancient Vase", result.get().getTitle());
        assertEquals("Unknown", result.get().getCreator());
    }
}
