package ru.practicum.event.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Новый комментарий")
public class CommentNewDto {

    @NotBlank
    @Length(min = 3, max = 500)
    @Schema(description = "Текст комментария")
    private String text;
}
