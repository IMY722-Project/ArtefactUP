package za.ac.up.artifactup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class Artefact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artefact_seq")
    @SequenceGenerator(name = "artefact_seq", sequenceName = "artefact_id_seq", allocationSize = 1)
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

    @Column(nullable = false)
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "artefact_field_link",
        joinColumns = {@JoinColumn(name = "artefact_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "artefact_field")
    @Column(name = "link")
    private Map<String,String> fieldAdditionalInfoHttpLinks;
}
