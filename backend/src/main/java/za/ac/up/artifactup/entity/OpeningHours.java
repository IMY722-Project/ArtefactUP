package za.ac.up.artifactup.entity;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opening_hours_seq")
    @SequenceGenerator(name = "opening_hours_seq", sequenceName = "opening_hours_id_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek day;

    @Column(nullable = false)
    private LocalTime openingTime; // Format: "HH:mm"

    @Column(nullable = false)
    private LocalTime closingTime; // Format: "HH:mm"
}
