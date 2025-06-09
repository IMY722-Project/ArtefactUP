package za.ac.up.artefactup.repository;

import za.ac.up.artefactup.entity.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CollectionRepositoryTest {

    @Autowired
    private CollectionRepository collectionRepository;

    @Test
    public void testSaveAndFind() {
        Collection obj = new Collection();
        collectionRepository.save(obj);

        List<Collection> results = collectionRepository.findAll();
        assertThat(results).isNotEmpty();
    }
}
