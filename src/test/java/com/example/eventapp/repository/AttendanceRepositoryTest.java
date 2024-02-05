package com.example.eventapp.repository;

import com.example.eventapp.pojo.entity.Attendance;
import com.example.eventapp.pojo.entity.Event;
import com.example.eventapp.pojo.entity.RSVPStatus;
import com.example.eventapp.pojo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains test cases for the AttendanceRepository using Spring Data JPA.
 * It tests the repository's ability to perform queries related to attendance records, such as finding attendances by user or event.
 */
@DataJpaTest
public class AttendanceRepositoryTest {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @Autowired
    private EventRepository eventRepository; // Assuming you have an EventRepository

    private Attendance attendance;
    private User user;
    private Event event;

    /**
     * This method is executed before each test case to set up User, Event, and Attendance entities with sample data.
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

        // Create Attendance entity
        attendance = new Attendance();
        attendance.setUser(user);
        attendance.setEvent(event);
        attendance.setRsvpStatus(RSVPStatus.ATTENDING); // Replace with the actual enum value you use
        // Set other necessary fields for Attendance
    }

    /**
     * Test case to verify that finding attendances by a specific user returns the expected attendances.
     */
    @Test
    public void whenFindByUser_thenReturnAttendances() {
        // Given: Saving the attendance record to the repository
        attendanceRepository.save(attendance);

        // When: Finding attendances by a specific user
        List<Attendance> foundAttendances = attendanceRepository.findByUserId(user.getId());

        // Then: Assert that the list of found attendances is not empty and contains the test attendance record
        assertThat(foundAttendances).isNotEmpty().contains(attendance);
    }

    /**
     * Test case to verify that finding attendances by a specific event returns the expected attendances.
     */
    @Test
    public void whenFindByEvent_thenReturnAttendances() {
        // Given: Saving the attendance record to the repository
        attendanceRepository.save(attendance);

        // When: Finding attendances by a specific event
        List<Attendance> foundAttendances = attendanceRepository.findByEventId(event.getId());

        // Then: Assert that the list of found attendances is not empty and contains the test attendance record
        assertThat(foundAttendances).isNotEmpty().contains(attendance);
    }
}