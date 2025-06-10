package za.ac.up.artifactup.unit.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Collection;
import za.ac.up.artifactup.repository.ArtefactRepository;
import za.ac.up.artifactup.service.CollectionService;
import za.ac.up.artifactup.service.impl.ArtefactServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ArtefactServiceTest {

    @Autowired
    private ArtefactServiceImpl artefactService;

    @MockBean
    private ArtefactRepository artefactRepository;

    @MockBean
    private CollectionService<Collection> collectionService;

    @Test
    void testFindAll() {
        Artefact a = new Artefact();
        when(artefactRepository.findAll()).thenReturn(List.of(a));
        List<Artefact> artefacts = artefactService.findAll();
        assertEquals(1, artefacts.size());
    }

    @Test
    void testCreate_WhenCollectionExists() {
        Collection collection = new Collection();
        collection.setName("Historic");
        Artefact artefact = new Artefact();
        artefact.setCollection(collection);

        when(collectionService.findByName("Historic")).thenReturn(Optional.of(collection));
        when(artefactRepository.save(artefact)).thenReturn(artefact);

        Artefact result = artefactService.create(artefact);
        assertEquals(collection, result.getCollection());
    }

    @Test
    void testCreate_WhenCollectionDoesNotExist() {
        Collection collection = new Collection();
        collection.setName("NewCollection");
        Artefact artefact = new Artefact();
        artefact.setCollection(collection);

        Collection saved = new Collection();
        saved.setName("NewCollection");

        when(collectionService.findByName("NewCollection")).thenReturn(Optional.empty());
        when(collectionService.saveCollection(collection)).thenReturn(saved);
        when(artefactRepository.save(any(Artefact.class))).thenAnswer(i -> i.getArgument(0));

        Artefact result = artefactService.create(artefact);
        assertEquals("NewCollection", result.getCollection().getName());
    }

    @Test
    void testFindAllArtifactsByCollectionName() {
        when(artefactRepository.findByCollectionName("Ancient")).thenReturn(List.of(new Artefact()));
        List<Artefact> results = artefactService.findAllArtifactsByCollectionName("Ancient");
        assertEquals(1, results.size());
    }

    @Test
    void testFindAllArtefactsByMuseumName() {
        when(artefactRepository.findAllByMuseumName("Louvre")).thenReturn(List.of(new Artefact()));
        List<Artefact> results = artefactService.findAllArtefactsByMuseumName("Louvre");
        assertEquals(1, results.size());
    }

    @Test
    void testDeleteById() {
        artefactService.deleteById(42L);
        verify(artefactRepository, times(1)).deleteById(42L);
    }

    @Test
    void testFindByTitle() {
        Artefact artefact = new Artefact();
        artefact.setTitle("Mask");

        when(artefactRepository.findByTitle("Mask")).thenReturn(Optional.of(artefact));
        Optional<Artefact> found = artefactService.findByTitle("Mask");

        assertTrue(found.isPresent());
        assertEquals("Mask", found.get().getTitle());
    }
}
