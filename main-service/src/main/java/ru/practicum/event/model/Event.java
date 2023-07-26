package ru.practicum.event.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.category.Category;
import ru.practicum.location.Location;
import ru.practicum.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENTS", schema = "PUBLIC")
@FieldDefaults(level = PRIVATE)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String annotation;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    Category category;

    @Column(name = "CONFIRMED_REQUESTS")
    Long confirmedRequests;

    @Column(name = "CREATED_ON")
    LocalDateTime createdOn;

    String description;

    @Column(name = "EVENT_DATE")
    LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "INITIATOR_ID")
    User initiator;

    @OneToOne
    @JoinColumn(name = "LOCATION_ID")
    Location location;

    Boolean paid;

    @Column(name = "PARTICIPANT_LIMIT")
    Long participantLimit;

    @Column(name = "PUBLISHED_ON")
    LocalDateTime publishedOn;

    @Column(name = "REQUEST_MODERATION")
    Boolean requestModeration;

    @Enumerated(EnumType.STRING)
    State state;

    String title;

    Long views;
}
