package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.CommentCreateRequest;
import com.example.eventapp.pojo.dto.CommentDetailResponse;
import com.example.eventapp.pojo.dto.CommentUpdateRequest;
import com.example.eventapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class handles HTTP requests related to comment operations.
 * It provides endpoints for creating, updating, retrieving, and deleting comments.
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Create a new comment based on the provided comment create request.
     * @param createRequest The comment create request containing comment details.
     * @return A ResponseEntity with the created CommentDetailResponse and HTTP status code 200 (OK).
     */
    @PostMapping
    public ResponseEntity<CommentDetailResponse> createComment(@RequestBody CommentCreateRequest createRequest) {
        CommentDetailResponse newComment = commentService.createComment(createRequest);
        return ResponseEntity.ok(newComment);
    }

    /**
     * Update an existing comment's information based on the provided comment ID and update request.
     * @param id The ID of the comment to be updated.
     * @param updateRequest The comment update request containing updated comment details.
     * @return A ResponseEntity with the updated CommentDetailResponse and HTTP status code 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommentDetailResponse> updateComment(@PathVariable Integer id, @RequestBody CommentUpdateRequest updateRequest) {
        CommentDetailResponse updatedComment = commentService.updateComment(id, updateRequest);
        return ResponseEntity.ok(updatedComment);
    }

    /**
     * Retrieve comment information by comment ID.
     * @param id The ID of the comment to retrieve.
     * @return A ResponseEntity with the CommentDetailResponse and HTTP status code 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentDetailResponse> getCommentById(@PathVariable Integer id) {
        CommentDetailResponse comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    /**
     * Retrieve a list of comments based on the associated post's ID.
     * @param postId The ID of the post to retrieve comments for.
     * @return A ResponseEntity with a list of CommentDetailResponse objects and HTTP status code 200 (OK).
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDetailResponse>> getCommentsByPost(@PathVariable Integer postId) {
        List<CommentDetailResponse> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Retrieve a list of comments based on the author's ID.
     * @param authorId The ID of the author to retrieve comments for.
     * @return A ResponseEntity with a list of CommentDetailResponse objects and HTTP status code 200 (OK).
     */
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<CommentDetailResponse>> getCommentsByAuthor(@PathVariable Integer authorId) {
        List<CommentDetailResponse> comments = commentService.getCommentsByAuthor(authorId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Delete a comment based on the provided comment ID.
     * @param id The ID of the comment to be deleted.
     * @return A ResponseEntity with no content and HTTP status code 200 (OK) upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}

