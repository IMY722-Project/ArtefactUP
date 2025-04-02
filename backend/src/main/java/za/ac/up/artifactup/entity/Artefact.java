package za.ac.up.artifactup.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Artefact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(nullable = false)
    private String creator;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "museum_id", nullable = false)
    private Museum museum;

    @Column(nullable = false)
    private String dateCreated;

    @Column(nullable = false)
    private String locationCreated;

    @Column(nullable = false)
    private String physicalDimensions;

    @Column(nullable = false)
    private String rights;

    @Column(nullable = false)
    private String medium;
}
