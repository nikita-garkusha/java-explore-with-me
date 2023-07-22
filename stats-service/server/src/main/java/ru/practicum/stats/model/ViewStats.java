package ru.practicum.stats.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ViewStats {

    String app;
    String uri;
    Long hits;
}
