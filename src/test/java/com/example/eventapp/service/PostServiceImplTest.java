package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.PostCreateRequest;
import com.example.eventapp.pojo.dto.PostDetailResponse;
import com.example.eventapp.pojo.dto.PostUpdateRequest;
import com.example.eventapp.pojo.entity.Event;
import com.example.eventapp.pojo.entity.Post;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.EventRepository;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private User user;
    private Event event;
    private Post post;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testUser");

        event = new Event();
        event.setId(1);
        event.setName("testEvent");

        post = new Post();
        post.setId(1);
        post.setContent("testContent");
        post.setAuthor(user);
        post.setEvent(event);
        // Set other fields as necessary
    }

    @Test
    public void createPostTest() {
        PostCreateRequest createRequest = new PostCreateRequest();

        // Assuming the PostCreateRequest has these fields
        createRequest.setAuthorId(1); // Use a valid author ID
        createRequest.setEventId(1); // Use a valid event ID
        createRequest.setContent("Test Content");
        createRequest.setImageUrl("http://example.com/image.jpg");

        // Mocking repository calls
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostDetailResponse response = postService.createPost(createRequest);

        assertThat(response).isNotNull();
        assertThat(response.getContent()).isEqualTo(post.getContent());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    public void updatePostTest() {
        PostUpdateRequest updateRequest = new PostUpdateRequest();

        // Populate the update request with test data
        updateRequest.setContent("Updated Content");
        updateRequest.setImageUrl("http://example.com/updated-image.jpg");

        // Assuming 'post' is already initialized in the setUp() method with id, content, etc.
        int postId = post.getId(); // Get the ID from the existing 'post' object

        // Mocking repository calls
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostDetailResponse response = postService.updatePost(postId, updateRequest);

        assertThat(response).isNotNull();
        assertThat(response.getContent()).isEqualTo(updateRequest.getContent());
        verify(postRepository, times(1)).save(any(Post.class));
    }


    @Test
    public void getPostByIdTest() {
        when(postRepository.findById(anyInt())).thenReturn(Optional.of(post));

        PostDetailResponse response = postService.getPostById(post.getId());

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(post.getId());
        verify(postRepository, times(1)).findById(anyInt());
    }

    @Test
    public void deletePostTest() {
        doNothing().when(postRepository).deleteById(anyInt());
        postService.deletePost(post.getId());
        verify(postRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void getPostsByAuthorTest() {
        List<Post> posts = Arrays.asList(post); // Assuming 'post' is initialized in setUp

        when(postRepository.findByAuthorId(anyInt())).thenReturn(posts);

        List<PostDetailResponse> responses = postService.getPostsByAuthor(user.getId());

        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getAuthor().getUsername()).isEqualTo(user.getUsername());
        verify(postRepository, times(1)).findByAuthorId(anyInt());
    }

    @Test
    public void getPostsByEventTest() {
        List<Post> posts = Arrays.asList(post); // Assuming 'post' is initialized in setUp

        when(postRepository.findByEventId(anyInt())).thenReturn(posts);

        List<PostDetailResponse> responses = postService.getPostsByEvent(event.getId());

        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getEvent().getName()).isEqualTo(event.getName());
        verify(postRepository, times(1)).findByEventId(anyInt());
    }
}

