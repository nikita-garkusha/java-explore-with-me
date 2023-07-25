package ru.practicum.event.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Параметры для поиска")
@FieldDefaults(level = PRIVATE)
public class CommentSearchParamsDto {
    @Schema(description = "Текст, присутствующий в комментарии")
    String text;

    @Schema(description = "Список id пользователей, чьи комментарии нужно найти")
    Set<Long> users;

    @Schema(description = "Список id эвентов, в которых будет вестись поиск")
    Set<Long> events;

    @Schema(description = "Дата и время не раньше которых должен быть опубликован комментарий")
    LocalDateTime rangeStart;

    @Schema(description = "Дата и время не позже которых должен быть опубликован комментарий")
    LocalDateTime rangeEnd;

    @Schema(description = "Количество комментариев, которые нужно пропустить для формирования текущего набора")
    Integer from;

    @Schema(description = "Количество комментариев в наборе")
    Integer size;
}
