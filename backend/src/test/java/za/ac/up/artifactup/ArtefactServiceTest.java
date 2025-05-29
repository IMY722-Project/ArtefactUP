package za.ac.artefactup.service;

import za.ac.artefactup.model.Artefact;
import za.ac.artefactup.repository.ArtefactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ArtefactServiceTest {

    @Mock
    private ArtefactRepository artefactRepository;

    @InjectMocks
    private ArtefactService artefactService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetArtefactById() {
        Artefact mockArtefact = new Artefact(1L, "Ancient Vase", "A vase from 500 BC");

        when(artefactRepository.findById(1L)).thenReturn(Optional.of(mockArtefact));

        Artefact result = artefactService.getArtefactById(1L);

        assertEquals("Ancient Vase", result.getName());
        assertEquals("A vase from 500 BC", result.getDescription());
    }
}
