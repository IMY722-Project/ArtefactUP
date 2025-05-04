package za.ac.up.artifactup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScavengerHuntStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hunt_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
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
