package ru.practicum.event.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.comment.dto.CommentDto;
import ru.practicum.exception.ApiError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Validated
@Tag(name = "Public: Comments", description = "Public API for working with comments")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentPublicController {
    private final CommentService commentService;


    @Operation(summary = "Viewing event comments", tags = "Public: Comments",
            description = "The endpoint returns complete information about all comments on the specified event\n" +
                    "\nIf no comments are found for the specified filters, returns an empty list.",
            operationId = "getAllCommentsByEventId")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @GetMapping("/{eventId}")
    public List<CommentDto> getAllByEventId(
            @PathVariable
            @Parameter(description = "event id") Long eventId,
            @RequestParam(required = false, defaultValue = "0")
            @Parameter(description = "The number of comments that need to be skipped to form the current set") Integer from,
            @RequestParam(required = false, defaultValue = "10")
            @Parameter(description = "Number of comments in the set") Integer size,
            HttpServletRequest httpServletRequest) {
        log.info("Received {} request to {} from {} ", httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRemoteAddr());
        return commentService.getAllByEventId(eventId, from, size);
    }
}
