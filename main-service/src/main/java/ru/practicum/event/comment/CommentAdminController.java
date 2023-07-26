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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.comment.dto.CommentDto;
import ru.practicum.event.comment.dto.CommentSearchParamsDto;
import ru.practicum.exception.ApiError;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@Tag(name = "Admin: Комментарии", description = "Админский API для работы с комментариями")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class CommentAdminController {
    private final CommentService commentService;

    @Operation(summary = "Deleting comments on request from the administrator",
            tags = "Admin: Comments",
            description = "When the owner of a comment is deleted, the comment is also deleted.\n" +
                    "\nWhen deleting an event, all comments to this event will be deleted.",
            operationId = "deleteCommentByAdminRequest")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Comment deleted"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class)
                            )
                    })
    })
    @DeleteMapping("{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Parameter(description = "id комментария") Long commentId,
                       HttpServletRequest httpServletRequest) {
        log.info("Received {} request to {} from {} ", httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRemoteAddr());
        commentService.deleteByAdminRequest(commentId);
    }

    @Operation(summary = "Search for comments by parameters",
            tags = "Admin: Comments",
            description = "The endpoint returns complete information about all comments that match the passed conditions.\n" +
                    "\nIf no comments are found for the specified filters, returns an empty list",
            operationId = "getAllCommentsByParams")
    @GetMapping()
    public List<CommentDto> getAllByParams(
            @RequestParam(required = false)
            @Parameter(description = "Text present in the comment") String text,
            @RequestParam(required = false)
            @Parameter(description = "List of user IDs whose comments need to be found") Set<Long> users,
            @RequestParam(required = false)
            @Parameter(description = "List of event IDs to be searched for") Set<Long> events,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @Parameter(description = "Date and time not earlier than when the comment should be published") LocalDateTime rangeStart,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @Parameter(description = "Date and time no later than which the comment should be published") LocalDateTime rangeEnd,
            @RequestParam(required = false, defaultValue = "0")
            @Parameter(description = "The number of comments that need to be skipped to form the current set") Integer from,
            @RequestParam(required = false, defaultValue = "10")
            @Parameter(description = "Number of comments in the set") Integer size,
            HttpServletRequest httpServletRequest) {
        log.info("Received {} request to {} from {} ", httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRemoteAddr());
        return commentService.getAllByParams(new CommentSearchParamsDto(text,
                users,
                events,
                rangeStart,
                rangeEnd,
                from,
                size));
    }
}
