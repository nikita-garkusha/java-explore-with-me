package ru.practicum.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Пользователь")
@FieldDefaults(level = PRIVATE)
public class UserShortDto {
    @Schema(description = "id пользователя")
    Long id;

    @Schema(description = "Имя пользователя")
    String name;
}
