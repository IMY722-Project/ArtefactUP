package za.ac.up.artefactup.repository;

import za.ac.up.artefactup.entity.Artefact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ArtefactRepositoryTest {

    @Autowired
    private ArtefactRepository artefactRepository;

    @Test
    public void testSaveAndFind() {
        Artefact artefact = new Artefact();
        artefact.setName("Test Artefact");
        artefact.setDescription("Test Description");
        artefactRepository.save(artefact);

        List<Artefact> results = artefactRepository.findAll();
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getName()).isEqualTo("Test Artefact");
    }
}
