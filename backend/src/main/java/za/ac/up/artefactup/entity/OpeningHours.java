package za.ac.up.artefactup.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class OpeningHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek day;

    @Column(nullable = false)
    private LocalTime openingTime; // Format: "HH:mm"

    @Column(nullable = false)
    private LocalTime closingTime; // Format: "HH:mm"
}
