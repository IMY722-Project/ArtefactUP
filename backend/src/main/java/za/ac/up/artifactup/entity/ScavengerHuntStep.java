package za.ac.up.artifactup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * TODO:
 * 1. Add S3 url for image
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScavengerHuntStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hunt_id", nullable = false)
    private ScavengerHunt hunt;

    @Column(nullable = false)
    private int stepNumber;

    @ManyToOne
    @JoinColumn(name = "artefact_id", nullable = false)
    private Artefact artefact;

    @ManyToOne
    @JoinColumn(name = "museum_id", nullable = false)
    private Museum museum;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String clue;
}
