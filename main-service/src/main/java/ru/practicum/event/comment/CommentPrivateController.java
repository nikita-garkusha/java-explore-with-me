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
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.comment.dto.CommentDto;
import ru.practicum.event.comment.dto.CommentNewDto;
import ru.practicum.exception.ApiError;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@Tag(name = "Private: Комментарии", description = "Closed API for working with comments")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/comments")
public class CommentPrivateController {
    private final CommentService commentService;

    @Operation(summary = "Adding a new comment to an event",
            tags = "Private: Comments",
            operationId = "createComment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@PathVariable
                             @Parameter(description = "event id") Long userId,
                             @PathVariable
                             @Parameter(description = "event id") Long eventId,
                             @RequestBody @Valid CommentNewDto commentNewDto,
                             HttpServletRequest httpServletRequest) {
        log.info("Received {} request to {} from {} ", httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRemoteAddr());
        return commentService.create(userId, eventId, commentNewDto);
    }

    @Operation(summary = "Updating the comment",
            tags = "Private: Comments",
            operationId = "updateComment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @PatchMapping("/{commentId}")
    public CommentDto update(@PathVariable
                             @Parameter(description = "user id") Long userId,
                             @PathVariable
                             @Parameter(description = "comment id") Long commentId,
                             @RequestBody @Valid CommentNewDto commentNewDto,
                             HttpServletRequest httpServletRequest) {
        log.info("Received {} request to {} from {} ", httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRemoteAddr());
        return commentService.update(userId, commentId, commentNewDto);
    }

    @Operation(summary = "Deleting comments on request from the user",
            tags = "Private: Comments",
            operationId = "deleteCommentByUserRequest")
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
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "409",
                    description = "The user is not the author of the comment",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class)
                            )
                    })
    })
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable
                           @Parameter(description = "user id") Long userId,
                           @PathVariable
                           @Parameter(description = "comment id") Long commentId,
                           HttpServletRequest httpServletRequest) {
        log.info("Received {} request to {} from {} ", httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRemoteAddr());
        commentService.deleteByUserRequest(userId, commentId);
    }

    @Operation(summary = "Getting user comments",
            tags = "Private: Comments",
            description = "The endpoint returns complete information about all comments left by the user.\n" +
                    "\nIf there are no comments, returns an empty list",
            operationId = "getAllCommentsByUserId")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiError.class))
                    })
    })
    @GetMapping()
    public List<CommentDto> getAllByUserId(@PathVariable Long userId,
                                           @RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           HttpServletRequest httpServletRequest) {
        log.info("Received {} request to {} from {} ", httpServletRequest.getMethod(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getRemoteAddr());
        return commentService.getAllByUserId(userId, from, size);
    }
}
