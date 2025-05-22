package za.ac.up.artifactup.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.up.artifactup.entity.ScavengerHunt;
import za.ac.up.artifactup.repository.ScavengerHuntRepository;
import za.ac.up.artifactup.service.ScavengerHuntService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScavengerHuntServiceImpl implements ScavengerHuntService<ScavengerHunt> {

    private final ScavengerHuntRepository huntRepo;

    @Override
    public List<ScavengerHunt> findAll() {
        return huntRepo.findAll();
    }

    @Override
    @Transactional
    public ScavengerHunt create(ScavengerHunt hunt) {
        return huntRepo.save(hunt);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        huntRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<ScavengerHunt> findByName(String name) {
        return huntRepo.findByName(name);
    }

    @Transactional
    @Override
    public Optional<ScavengerHunt> findById(Long id) {
        return huntRepo.findById(id);
    }

    @Override
    public int count() {
        return (int) huntRepo.count();
    }
}
