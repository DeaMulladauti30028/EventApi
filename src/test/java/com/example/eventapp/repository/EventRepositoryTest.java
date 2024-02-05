package com.example.eventapp.repository;

import com.example.eventapp.pojo.entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains test cases for the EventRepository using Spring Data JPA.
 * It tests the repository's ability to perform queries related to events, such as finding events by location.
 */
@DataJpaTest
@TestPropertySource(locations = "classpath:application.properties")
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    private Event event;

    /**
     * This method is executed before each test case to set up an Event entity with sample data.
     */
    @BeforeEach
    public void setUp() {
        // Create an Event entity instance
        event = new Event();
        event.setLocation("Test Location");
        // Set other necessary fields of your Event entity
    }

    /**
     * Test case to verify that finding events by location returns the expected events.
     */
    @Test
    public void whenFindByLocation_thenReturnEvents() {
        // Given: Saving the event to the repository
        eventRepository.save(event);

        // When: Finding events by location
        List<Event> foundEvents = eventRepository.findByLocation(event.getLocation());

        // Then: Assert that the list of found events is not empty and the location matches the expected location
        assertThat(foundEvents).isNotEmpty();
        assertThat(foundEvents.get(0).getLocation()).isEqualTo(event.getLocation());
    }

    /**
     * Test case to verify that finding events by an invalid location returns an empty list.
     */
    @Test
    public void whenFindByInvalidLocation_thenReturnEmptyList() {
        // When: Finding events by a nonexistent location
        List<Event> fromDb = eventRepository.findByLocation("Nonexistent Location");

        // Then: Assert that the result is an empty list
        assertThat(fromDb).isEmpty();
    }

    // Additional tests can be written for other scenarios like
    // testing constraints, handling null values, etc.
}

