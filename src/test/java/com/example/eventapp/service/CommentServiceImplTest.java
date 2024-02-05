package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.CommentCreateRequest;
import com.example.eventapp.pojo.dto.CommentDetailResponse;
import com.example.eventapp.pojo.dto.CommentUpdateRequest;
import com.example.eventapp.pojo.entity.Comment;
import com.example.eventapp.pojo.entity.Post;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.CommentRepository;
import com.example.eventapp.repository.PostRepository;
import com.example.eventapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment comment;
    private User user;
    private Post post;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testUser");

        post = new Post();
        post.setId(1);

        comment = new Comment();
        comment.setId(1);
        comment.setContent("Test Comment");
        comment.setAuthor(user);
        comment.setPost(post);
    }

    @Test
    public void createCommentTest() {
        CommentCreateRequest createRequest = new CommentCreateRequest();
        createRequest.setContent("Test Comment");
        createRequest.setAuthorId(1);
        createRequest.setPostId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentDetailResponse response = commentService.createComment(createRequest);

        assertThat(response).isNotNull();
        assertThat(response.getContent()).isEqualTo(comment.getContent());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void updateCommentTest() {
        CommentUpdateRequest updateRequest = new CommentUpdateRequest();
        updateRequest.setContent("Updated Comment");

        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentDetailResponse response = commentService.updateComment(1, updateRequest);

        assertThat(response).isNotNull();
        assertThat(response.getContent()).isEqualTo(updateRequest.getContent());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void deleteCommentTest() {
        doNothing().when(commentRepository).deleteById(1);
        commentService.deleteComment(1);
        verify(commentRepository, times(1)).deleteById(1);
    }

    @Test
    public void getCommentByIdTest() {
        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));

        CommentDetailResponse response = commentService.getCommentById(1);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(comment.getId());
        verify(commentRepository, times(1)).findById(1);
    }

    @Test
    public void getCommentsByPostTest() {
        when(commentRepository.findByPostId(1)).thenReturn(Arrays.asList(comment));

        List<CommentDetailResponse> responses = commentService.getCommentsByPost(1);

        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getContent()).isEqualTo(comment.getContent());
    }

    @Test
    public void getCommentsByAuthorTest() {
        when(commentRepository.findByAuthorId(1)).thenReturn(Arrays.asList(comment));

        List<CommentDetailResponse> responses = commentService.getCommentsByAuthor(1);

        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getContent()).isEqualTo(comment.getContent());
    }
}
