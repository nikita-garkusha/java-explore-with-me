package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.event.model.State;
import ru.practicum.location.LocationDto;
import ru.practicum.user.dto.UserDto;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class EventDto {
    final String dataFormat = "yyyy-MM-dd HH:mm:ss";
    Long id;
    String annotation;
    CategoryDto category;
    Long confirmedRequests;
    @JsonFormat(pattern = dataFormat)
    LocalDateTime createdOn;
    String description;
    @JsonFormat(pattern = dataFormat)
    LocalDateTime eventDate;
    UserDto initiator;
    LocationDto location;
    boolean paid;
    Long participantLimit;
    @JsonFormat(pattern = dataFormat)
    LocalDateTime publishedOn;
    boolean requestModeration;
    State state;
    String title;
    Long views;
}
