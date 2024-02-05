package com.example.eventapp.repository;

import com.example.eventapp.pojo.entity.Comment;
import com.example.eventapp.pojo.entity.Post;
import com.example.eventapp.pojo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains test cases for the CommentRepository using Spring Data JPA.
 * It tests the repository's ability to perform queries related to comments, such as finding comments by post or author.
 */
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @Autowired
    private PostRepository postRepository; // Assuming you have a PostRepository

    private Comment comment;
    private User author;
    private Post post;

    /**
     * This method is executed before each test case to set up User, Post, and Comment entities with sample data.
     */
    @BeforeEach
    public void setUp() {
        // Create User and Post entities
        author = new User();
        // Set necessary fields for User
        userRepository.save(author);

        post = new Post();
        // Set necessary fields for Post
        // Ensure Post is related to a User if required
        postRepository.save(post);

        // Create Comment entity
        comment = new Comment();
        comment.setAuthor(author);
        comment.setPost(post);
        comment.setContent("Test Comment");
        comment.setTimestamp(LocalDateTime.now());
    }

    /**
     * Test case to verify that finding comments by a specific post returns the expected comments.
     */
    @Test
    public void whenFindByPost_thenReturnComments() {
        // Given: Saving the comment to the repository
        commentRepository.save(comment);

        // When: Finding comments by a specific post
        List<Comment> foundComments = commentRepository.findByPostId(post.getId());

        // Then: Assert that the list of found comments is not empty and contains the test comment
        assertThat(foundComments).isNotEmpty().contains(comment);
    }

    /**
     * Test case to verify that finding comments by a specific author returns the expected comments.
     */
    @Test
    public void whenFindByAuthor_thenReturnComments() {
        // Given: Saving the comment to the repository
        commentRepository.save(comment);

        // When: Finding comments by a specific author
        List<Comment> foundComments = commentRepository.findByAuthorId(author.getId());

        // Then: Assert that the list of found comments is not empty and contains the test comment
        assertThat(foundComments).isNotEmpty().contains(comment);
    }
}
