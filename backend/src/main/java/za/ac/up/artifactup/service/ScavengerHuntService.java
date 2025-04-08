package za.ac.up.artifactup.service;

import java.util.List;
import java.util.Optional;

public interface ScavengerHuntService<T> {

    List<T> findAll();

    T create(T hunt);

//    T startHunt(Long huntId, String cognitoUserId);
//
//    T validateStep(Long huntId, String cognitoUserId, String qrCode);
//
//    T getUserProgress(Long huntId, String cognitoUserId);

    void deleteById(Long id);

    Optional<T> findByName(String name);
}

