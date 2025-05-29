package za.ac.artefactup.service;

import za.ac.artefactup.model.Artifact;
import za.ac.artefactup.repository.ArtifactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ArtifactServiceTest {

    @Mock
    private ArtifactRepository artifactRepository;

    @InjectMocks
    private ArtifactService artifactService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetArtifactById() {
        Artifact mockArtifact = new Artifact(1L, "Ancient Vase", "A vase from 500 BC");

        when(artifactRepository.findById(1L)).thenReturn(Optional.of(mockArtifact));

        Artifact result = artifactService.getArtifactById(1L);

        assertEquals("Ancient Vase", result.getName());
        assertEquals("A vase from 500 BC", result.getDescription());
    }
}
