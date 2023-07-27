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
    @Schema(description = "Text present in the comment")
    String text;

    @Schema(description = "List of user IDs whose comments need to be found")
    Set<Long> users;

    @Schema(description = "List of event IDs to be searched for")
    Set<Long> events;

    @Schema(description = "Date and time not earlier than when the comment should be published")
    LocalDateTime rangeStart;

    @Schema(description = "Date and time no later than which the comment should be published")
    LocalDateTime rangeEnd;

    @Schema(description = "The number of comments that need to be skipped to form the current set")
    Integer from;

    @Schema(description = "Number of comments in the set")
    Integer size;
}
