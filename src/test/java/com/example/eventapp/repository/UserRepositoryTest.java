package com.example.eventapp.repository;

import com.example.eventapp.pojo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains test cases for the UserRepository using Spring Data JPA.
 * It tests the repository's ability to perform CRUD operations and custom queries.
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    /**
     * This method is executed before each test case to set up a User entity with sample data.
     */
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setName("Test User");
        // Set other necessary fields of your User entity
    }

    /**
     * Test case to verify that finding a user by username returns the expected user.
     */
    @Test
    public void whenFindByUsername_thenReturnUser() {
        // Given: Saving the user to the repository
        userRepository.save(user);

        // When: Finding a user by username
        User found = userRepository.findByUsername(user.getUsername());

        // Then: Assert that the found user's username matches the expected username
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

    /**
     * Test case to verify that finding a user by email returns the expected user.
     */
    @Test
    public void whenFindByEmail_thenReturnUser() {
        // Given: Saving the user to the repository
        userRepository.save(user);

        // When: Finding a user by email
        User found = userRepository.findByEmail(user.getEmail());

        // Then: Assert that the found user's email matches the expected email
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }

    /**
     * Test case to verify that finding a user by name returns the expected user.
     */
    @Test
    public void whenFindByName_thenReturnUser() {
        // Given: Saving the user to the repository
        userRepository.save(user);

        // When: Finding a user by name
        User found = userRepository.findByName(user.getName());

        // Then: Assert that the found user's name matches the expected name
        assertThat(found.getName()).isEqualTo(user.getName());
    }

    /**
     * Test case to verify that finding a user with an invalid username returns null.
     */
    @Test
    public void whenInvalidUsername_thenReturnNull() {
        // When: Finding a user with a non-existing username
        User fromDb = userRepository.findByUsername("doesNotExist");

        // Then: Assert that the result is null
        assertThat(fromDb).isNull();
    }
}