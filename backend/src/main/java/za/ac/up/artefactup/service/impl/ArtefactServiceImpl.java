package za.ac.up.artefactup.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import za.ac.up.artefactup.entity.Artefact;
import za.ac.up.artefactup.entity.Collection;
import za.ac.up.artefactup.repository.ArtefactRepository;
import za.ac.up.artefactup.service.ArtefactService;
import za.ac.up.artefactup.service.CollectionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtefactServiceImpl implements ArtefactService<Artefact> {

    private final ArtefactRepository repository;

    private final CollectionService<Collection> collectionService;

    @Override
    public List<Artefact> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Artefact create(Artefact artefact) {
        Collection collection = artefact.getCollection();

        Optional<Collection> foundCollection = collectionService.findByName(collection.getName().trim());

        if (foundCollection.isPresent()) {
            collection = foundCollection.get();
        } else {
            collection = collectionService.saveCollection(collection);
        }
        artefact.setCollection(collection);
        return repository.save(artefact);
    }

    @Override
    public List<Artefact> findAllArtifactsByCollectionName(String collectionName) {
        return repository.findByCollectionName(collectionName);
    }

    @Override
    public List<Artefact> findAllArtefactsByMuseumName(String museumName) {
        return repository.findAllByMuseumName(museumName);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Artefact> findByTitle(String name) {
        return repository.findByTitle(name);
    }
}
