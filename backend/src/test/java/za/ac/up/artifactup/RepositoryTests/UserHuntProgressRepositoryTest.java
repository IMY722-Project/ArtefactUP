package za.ac.up.artefactup.repository;

import za.ac.up.artefactup.entity.UserHuntProgress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserHuntProgressRepositoryTest {

    @Autowired
    private UserHuntProgressRepository userHuntProgressRepository;

    @Test
    public void testSaveAndFind() {
        UserHuntProgress obj = new UserHuntProgress();
        userHuntProgressRepository.save(obj);

        List<UserHuntProgress> results = userHuntProgressRepository.findAll();
        assertThat(results).isNotEmpty();
    }
}
