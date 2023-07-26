package ru.practicum.event.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.user.dto.UserShortDto;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Комментарий")
@FieldDefaults(level = PRIVATE)
public class CommentDto {

    @Schema(description = "id комментария")
    Long id;

    @Schema(description = "Текст комментария")
    String text;

    UserShortDto author;

    @Schema(description = "id события")
    Long eventId;

    @Schema(description = "Дата создания")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime created;
}
