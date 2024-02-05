package com.example.eventapp.repository;

import com.example.eventapp.pojo.entity.Event;
import com.example.eventapp.pojo.entity.Post;
import com.example.eventapp.pojo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains test cases for the PostRepository using Spring Data JPA.
 * It tests the repository's ability to perform queries related to posts, such as finding posts by author or event.
 */
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @Autowired
    private EventRepository eventRepository; // Assuming you have an EventRepository

    private Post post;
    private User user;
    private Event event;

    /**
     * This method is executed before each test case to set up User, Event, and Post entities with sample data.
     */
    @BeforeEach
    public void setUp() {
        // Create User and Event entities
        user = new User();
        // Set necessary fields for User
        userRepository.save(user);

        event = new Event();
        // Set necessary fields for Event
        eventRepository.save(event);

        // Create Post entity
        post = new Post();
        post.setAuthor(user);
        post.setEvent(event);
        // Set other necessary fields for Post
    }

    /**
     * Test case to verify that finding posts by author ID returns the expected posts.
     */
    @Test
    public void whenFindByAuthor_thenReturnPosts() {
        // Given: Saving the post to the repository
        postRepository.save(post);

        // When: Finding posts by author ID
        List<Post> foundPosts = postRepository.findByAuthorId(user.getId());

        // Then: Assert that the list of found posts is not empty and contains the expected post
        assertThat(foundPosts).isNotEmpty().contains(post);
    }

    /**
     * Test case to verify that finding posts by event ID returns the expected posts.
     */
    @Test
    public void whenFindByEvent_thenReturnPosts() {
        // Given: Saving the post to the repository
        postRepository.save(post);

        // When: Finding posts by event ID
        List<Post> foundPosts = postRepository.findByEventId(event.getId());

        // Then: Assert that the list of found posts is not empty and contains the expected post
        assertThat(foundPosts).isNotEmpty().contains(post);
    }
}