package com.artefactup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.artefactup.model.Artifact;
import com.artefactup.repository.ArtifactRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class ArtifactServiceTest {

    @Mock
    private ArtifactRepository artifactRepository;

    @InjectMocks
    private ArtifactService artifactService;

    @Test
    public void testGetArtifactById() {
        Artifact artifact = new Artifact(1L, "Ancient Vase", "A vase from 500 BC");
        when(artifactRepository.findById(1L)).thenReturn(Optional.of(artifact));

        Artifact result = artifactService.getArtifactById(1L);
        assertEquals("Ancient Vase", result.getName());
    }
}
