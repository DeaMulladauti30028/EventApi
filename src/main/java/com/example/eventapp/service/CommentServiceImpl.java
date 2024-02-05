package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.CommentCreateRequest;
import com.example.eventapp.pojo.dto.CommentDetailResponse;
import com.example.eventapp.pojo.dto.CommentUpdateRequest;
import com.example.eventapp.pojo.dto.UserDTO;
import com.example.eventapp.pojo.entity.Comment;
import com.example.eventapp.pojo.entity.Post;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.CommentRepository;
import com.example.eventapp.repository.PostRepository;
import com.example.eventapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /**
     * Create a new comment based on the provided comment create request.
     * @param createRequest The comment create request containing comment details.
     * @return A CommentDetailResponse object with comment details.
     * @throws RuntimeException if the author user or post is not found.
     */
    @Override
    public CommentDetailResponse createComment(CommentCreateRequest createRequest) {
        // Create a new Comment object and populate it with request data
        Comment comment = new Comment();
        comment.setContent(createRequest.getContent());

        // Find the author user by ID, or throw an exception if not found
        User author = userRepository.findById(createRequest.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        comment.setAuthor(author);

        // Find the associated post by ID, or throw an exception if not found
        Post post = postRepository.findById(createRequest.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);

        // Save the comment to the database
        Comment savedComment = commentRepository.save(comment);

        // Convert the saved comment to a CommentDetailResponse object
        return convertToCommentDetailResponse(savedComment);
    }

    /**
     * Convert a Comment object to a CommentDetailResponse object.
     * @param comment The Comment object to convert.
     * @return A CommentDetailResponse object with comment details.
     */
    private CommentDetailResponse convertToCommentDetailResponse(Comment comment) {
        CommentDetailResponse response = new CommentDetailResponse();
        response.setId(comment.getId());
        response.setContent(comment.getContent());
        response.setTimestamp(comment.getTimestamp());

        if (comment.getAuthor() != null) {
            // Assuming UserDTO has a constructor or a method to create it from User entity
            UserDTO authorDto = new UserDTO(comment.getAuthor().getUsername());
            response.setAuthor(authorDto);
        }

        if (comment.getPost() != null) {
            // Set only the ID of the associated post
            response.setPostId(comment.getPost().getId());
        }

        return response;
    }

    /**
     * Update an existing comment based on the provided comment ID and update request.
     * @param commentId The ID of the comment to be updated.
     * @param updateRequest The comment update request containing updated comment details.
     * @return A CommentDetailResponse object with updated comment details.
     * @throws RuntimeException if the comment is not found.
     */
    @Override
    public CommentDetailResponse updateComment(Integer commentId, CommentUpdateRequest updateRequest) {
        // Find the comment by its ID, or throw an exception if not found
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Update the comment's content
        comment.setContent(updateRequest.getContent());

        // Note: Typically, the author and the associated post are not updated

        // Save the updated comment to the database
        Comment updatedComment = commentRepository.save(comment);

        // Convert the updated comment to a CommentDetailResponse object
        return convertToCommentDetailResponse(updatedComment);
    }

    /**
     * Delete a comment based on the provided comment ID.
     * @param commentId The ID of the comment to be deleted.
     */
    @Override
    public void deleteComment(Integer commentId) {
        // Delete the comment from the database by ID
        commentRepository.deleteById(commentId);
    }

    /**
     * Retrieve a comment by its ID.
     * @param commentId The ID of the comment to retrieve.
     * @return A CommentDetailResponse object with comment details.
     * @throws RuntimeException if the comment is not found.
     */
    @Override
    public CommentDetailResponse getCommentById(Integer commentId) {
        // Find the comment by its ID, or throw an exception if not found
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return convertToCommentDetailResponse(comment);
    }

    /**
     * Retrieve a list of comments by the associated post ID.
     * @param postId The ID of the associated post to retrieve comments for.
     * @return A list of CommentDetailResponse objects with comment details.
     */
    @Override
    public List<CommentDetailResponse> getCommentsByPost(Integer postId) {
        // Find comments by the associated post ID
        List<Comment> comments = commentRepository.findByPostId(postId);

        // Convert the list of comments to a list of CommentDetailResponse objects
        return comments.stream()
                .map(this::convertToCommentDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a list of comments by the author's user ID.
     * @param authorId The ID of the author user to retrieve comments for.
     * @return A list of CommentDetailResponse objects with comment details.
     */
    @Override
    public List<CommentDetailResponse> getCommentsByAuthor(Integer authorId) {
        // Find comments by the author's user ID
        List<Comment> comments = commentRepository.findByAuthorId(authorId);

        // Convert the list of comments to a list of CommentDetailResponse objects
        return comments.stream()
                .map(this::convertToCommentDetailResponse)
                .collect(Collectors.toList());
    }
}
