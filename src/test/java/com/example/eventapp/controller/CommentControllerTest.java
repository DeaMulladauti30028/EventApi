package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.CommentCreateRequest;
import com.example.eventapp.pojo.dto.CommentDetailResponse;
import com.example.eventapp.pojo.dto.CommentUpdateRequest;
import com.example.eventapp.pojo.dto.UserDTO;
import com.example.eventapp.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CommentDetailResponse commentDetailResponse;

    @BeforeEach
    public void setUp() {
        commentDetailResponse = new CommentDetailResponse();
        commentDetailResponse.setId(1);
        commentDetailResponse.setContent("Test Comment");
        commentDetailResponse.setTimestamp(LocalDateTime.now());
        commentDetailResponse.setAuthor(new UserDTO("testUser")); // Assuming UserDTO has a constructor or setter method
        commentDetailResponse.setPostId(1);
    }   // populate commentDetailResponse with test data

    @Test
    public void createCommentTest() throws Exception {
        CommentCreateRequest createRequest = new CommentCreateRequest();
        createRequest.setContent("Test Comment");
        createRequest.setAuthorId(1);
        createRequest.setPostId(1);

        when(commentService.createComment(any(CommentCreateRequest.class))).thenReturn(commentDetailResponse);

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Test Comment"))
                .andExpect(jsonPath("$.author.username").value("testUser"))
                .andExpect(jsonPath("$.postId").value(1));

        verify(commentService, times(1)).createComment(any(CommentCreateRequest.class));
    }

    @Test
    public void updateCommentTest() throws Exception {
        int commentId = 1;
        CommentUpdateRequest updateRequest = new CommentUpdateRequest();
        updateRequest.setContent("Updated Comment");

        // Setting up the expected CommentDetailResponse after update
        commentDetailResponse.setContent("Updated Comment"); // Assuming the update modifies the content

        when(commentService.updateComment(eq(commentId), any(CommentUpdateRequest.class))).thenReturn(commentDetailResponse);

        mockMvc.perform(put("/comments/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated Comment"));

        verify(commentService, times(1)).updateComment(eq(commentId), any(CommentUpdateRequest.class));
    }


    @Test
    public void getCommentByIdTest() throws Exception {
        int commentId = 1;

        when(commentService.getCommentById(commentId)).thenReturn(commentDetailResponse);

        mockMvc.perform(get("/comments/{id}", commentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(commentDetailResponse.getContent()));

        verify(commentService, times(1)).getCommentById(commentId);
    }

    @Test
    public void getCommentsByPostTest() throws Exception {
        int postId = 1;
        List<CommentDetailResponse> comments = Arrays.asList(commentDetailResponse);

        when(commentService.getCommentsByPost(postId)).thenReturn(comments);

        mockMvc.perform(get("/comments/post/{postId}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content").value(commentDetailResponse.getContent()));

        verify(commentService, times(1)).getCommentsByPost(postId);
    }

    @Test
    public void getCommentsByAuthorTest() throws Exception {
        int authorId = 1;
        List<CommentDetailResponse> comments = Arrays.asList(commentDetailResponse);

        when(commentService.getCommentsByAuthor(authorId)).thenReturn(comments);

        mockMvc.perform(get("/comments/author/{authorId}", authorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content").value(commentDetailResponse.getContent()));

        verify(commentService, times(1)).getCommentsByAuthor(authorId);
    }

    @Test
    public void deleteCommentTest() throws Exception {
        int commentId = 1;

        doNothing().when(commentService).deleteComment(commentId);

        mockMvc.perform(delete("/comments/{id}", commentId))
                .andExpect(status().isOk());

        verify(commentService, times(1)).deleteComment(commentId);
    }
}

