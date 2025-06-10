package za.ac.up.artefactup.repository;

import za.ac.up.artefactup.entity.Museum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MuseumRepositoryTest {

    @Autowired
    private MuseumRepository museumRepository;

    @Test
    public void testSaveAndFind() {
        Museum obj = new Museum();
        museumRepository.save(obj);

        List<Museum> results = museumRepository.findAll();
        assertThat(results).isNotEmpty();
    }
}
