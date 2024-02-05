package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.*;
import com.example.eventapp.service.PostService;
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
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private PostDetailResponse postDetailResponse;

    @BeforeEach
    public void setUp() {
        postDetailResponse = new PostDetailResponse();
        // Assuming UserDTO and EventDTO are set up correctly
        postDetailResponse.setId(1); // Example ID
        postDetailResponse.setContent("Test Post Content");
        postDetailResponse.setImageUrl("http://example.com/image.jpg");
        postDetailResponse.setTimestamp(LocalDateTime.now()); // Example timestamp
        postDetailResponse.setAuthor(new UserDTO("AuthorName")); // Example author
        postDetailResponse.setEvent(new EventDTO("EventName")); // Example event
    }

    @Test
    public void createPostTest() throws Exception {
        PostCreateRequest createRequest = new PostCreateRequest("Test Content", "http://example.com/image.jpg", 1, 1);

        when(postService.createPost(any(PostCreateRequest.class))).thenReturn(postDetailResponse);

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Test Post Content"))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/image.jpg"))
                .andExpect(jsonPath("$.author.username").value("AuthorName"))
                .andExpect(jsonPath("$.event.name").value("EventName"));

        verify(postService, times(1)).createPost(any(PostCreateRequest.class));
    }

    @Test
    public void updatePostTest() throws Exception {
        PostUpdateRequest updateRequest = new PostUpdateRequest();
        // Populate updateRequest with test data
        int postId = 1;

        when(postService.updatePost(eq(postId), any(PostUpdateRequest.class))).thenReturn(postDetailResponse);

        mockMvc.perform(put("/posts/{id}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Test Post Content"));

        verify(postService, times(1)).updatePost(eq(postId), any(PostUpdateRequest.class));
    }

    @Test
    public void getPostByIdTest() throws Exception {
        int postId = 1;

        when(postService.getPostById(postId)).thenReturn(postDetailResponse);

        mockMvc.perform(get("/posts/{id}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(postDetailResponse.getContent())); // Adjusted to 'content'

        verify(postService, times(1)).getPostById(postId);
    }

    @Test
    public void getPostsByAuthorTest() throws Exception {
        int authorId = 1;
        List<PostDetailResponse> posts = Arrays.asList(postDetailResponse);

        when(postService.getPostsByAuthor(authorId)).thenReturn(posts);

        mockMvc.perform(get("/posts/author/{authorId}", authorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content").value(postDetailResponse.getContent())); // Adjusted to 'content'

        verify(postService, times(1)).getPostsByAuthor(authorId);
    }

    @Test
    public void getPostsByEventTest() throws Exception {
        int eventId = 1;
        List<PostDetailResponse> posts = Arrays.asList(postDetailResponse);

        when(postService.getPostsByEvent(eventId)).thenReturn(posts);

        mockMvc.perform(get("/posts/event/{eventId}", eventId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content").value(postDetailResponse.getContent())); // Adjusted to 'content'

        verify(postService, times(1)).getPostsByEvent(eventId);
    }

    @Test
    public void deletePostTest() throws Exception {
        int postId = 1;

        doNothing().when(postService).deletePost(postId);

        mockMvc.perform(delete("/posts/{id}", postId))
                .andExpect(status().isOk());

        verify(postService, times(1)).deletePost(postId);
    }
}
