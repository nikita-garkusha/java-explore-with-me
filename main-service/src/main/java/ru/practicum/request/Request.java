package ru.practicum.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.event.model.Event;
import ru.practicum.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REQUESTS", schema = "PUBLIC")
@FieldDefaults(level = PRIVATE)
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID")
    Event event;

    @ManyToOne
    @JoinColumn(name = "REQUESTER_ID")
    User requester;

    LocalDateTime created;

    @Enumerated(EnumType.STRING)
    Status status;
}