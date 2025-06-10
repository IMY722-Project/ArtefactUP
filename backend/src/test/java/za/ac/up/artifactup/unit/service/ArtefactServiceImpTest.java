package za.ac.up.artifactup.unit.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.repository.ArtefactRepository;
import za.ac.up.artifactup.service.CollectionService;
import za.ac.up.artifactup.service.impl.ArtefactServiceImpl;

public class ArtefactServiceImpTest {

    @Mock
    private ArtefactRepository artefactRepository;

    @Mock
    private CollectionService<Collection> collectionService;

    @InjectMocks
    private ArtefactServiceImpl artefactService;

    private Artefact artefact;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        artefact = new Artefact();
        artefact.setId(1L);
        artefact.setTitle("Test Artefact");

        Collection collection = new Collection();
        collection.setId(1L);
        artefact.setCollection(collection);
    }

    @Test
    public void testFindAll() {
        when(artefactRepository.findAll()).thenReturn(Arrays.asList(artefact));

        List<Artefact> artefacts = artefactService.findAll();

        assertNotNull(artefacts);
        assertEquals(1, artefacts.size());
        assertEquals("Test Artefact", artefacts.get(0).getTitle());
    }

    @Test
    public void testCreate() {
        when(artefactRepository.save(any(Artefact.class))).thenReturn(artefact);

        Artefact result = artefactService.create(artefact);

        assertNotNull(result);
        assertEquals("Test Artefact", result.getTitle());
    }
}
