package za.ac.up.artefactup.service;

import za.ac.up.artefactup.entity.Collection;
import za.ac.up.artefactup.repository.CollectionRepository;
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
public class CollectionServiceTest {

    @Mock
    private CollectionRepository collectionRepository;

    @InjectMocks
    private CollectionService collectionService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Collection entity = new Collection();
        entity.setId(1L);
        when(collectionRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = collectionService.getCollectionById(1L);
        assertEquals(1L, result.getId());
    }
}
