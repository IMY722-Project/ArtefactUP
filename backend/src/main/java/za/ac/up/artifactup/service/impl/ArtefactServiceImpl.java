package za.ac.up.artifactup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.up.artifactup.dto.ArtefactMapper;
import za.ac.up.artifactup.entity.Artefact;
import za.ac.up.artifactup.entity.Category;
import za.ac.up.artifactup.repository.ArtefactRepository;
import za.ac.up.artifactup.service.ArtefactService;
import za.ac.up.artifactup.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtefactServiceImpl implements ArtefactService<Artefact> {

  private final ArtefactRepository artefactRepository;

  private final CategoryService categoryService;

  private final ArtefactMapper artefactMapper;

  @Override
  public List<Artefact> findAll() {
    return artefactRepository.findAll();
  }

  @Override
  public Artefact saveArtefact(Artefact artefact) {
    Category category = categoryService.findByName(artefact.getCategory().getName())
                                       .orElse(categoryService.saveCategory(artefact.getCategory()));
    artefact.setCategory(category);
    return artefactRepository.save(artefact);
  }

  @Override
  public List<Artefact> findAllArtifactsByCategoryId(Long categoryId) {
    return artefactRepository.findByCategoryId(categoryId);
  }

}
