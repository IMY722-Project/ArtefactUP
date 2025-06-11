package za.ac.up.artifactup.unit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.entity.Museum;
import za.ac.up.artifactup.repository.ArtefactRepository;
import za.ac.up.artifactup.service.CollectionService;
import za.ac.up.artifactup.service.impl.ArtefactServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtefactServiceImplTest {

    @Mock
    private ArtefactRepository artefactRepository;

    @Mock
    private CollectionService<Collection> collectionService;

    private ArtefactServiceImpl artefactService;

    @BeforeEach
    void setUp() {
        artefactService = new ArtefactServiceImpl(artefactRepository, collectionService);
    }

    @Test
    void testFindAll() {
        Artefact artefact = new Artefact();
        artefact.setId(1L);
        artefact.setTitle("Test Artefact");
        artefact.setImageUrl("test.jpg");

        when(artefactRepository.findAll()).thenReturn(Collections.singletonList(artefact));

        List<Artefact> result = artefactService.findAll();

        assertEquals(1, result.size());
        assertEquals("Test Artefact", result.get(0).getTitle());
    }

    @Test
    void testCreate() {
        // Create test data
        Collection collection = new Collection();
        collection.setName("Test Collection");

        Museum museum = new Museum();
        museum.setName("Test Museum");

        Artefact artefact = new Artefact();
        artefact.setTitle("Test Artefact");
        artefact.setCollection(collection);
        artefact.setMuseum(museum);

        when(collectionService.findByName("Test Collection")).thenReturn(Optional.of(collection));
        when(artefactRepository.save(any(Artefact.class))).thenReturn(artefact);

        Artefact result = artefactService.create(artefact);

        assertNotNull(result);
        assertEquals("Test Artefact", result.getTitle());
        verify(artefactRepository, times(1)).save(any(Artefact.class));
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        doNothing().when(artefactRepository).deleteById(id);

        artefactService.deleteById(id);

        verify(artefactRepository, times(1)).deleteById(id);
    }
}