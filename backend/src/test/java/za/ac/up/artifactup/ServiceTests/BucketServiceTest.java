package za.ac.up.artefactup.service;

import za.ac.up.artefactup.entity.Bucket;
import za.ac.up.artefactup.repository.BucketRepository;
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
public class BucketServiceTest {

    @Mock
    private BucketRepository bucketRepository;

    @InjectMocks
    private BucketService bucketService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Bucket entity = new Bucket();
        entity.setId(1L);
        when(bucketRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = bucketService.getBucketById(1L);
        assertEquals(1L, result.getId());
    }
}
