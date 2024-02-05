package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.EventCreateRequest;
import com.example.eventapp.pojo.dto.EventDetailResponse;
import com.example.eventapp.pojo.dto.EventUpdateRequest;
import com.example.eventapp.pojo.entity.Event;
import com.example.eventapp.pojo.entity.EventCategory;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event event;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);

        event = new Event();
        event.setId(1);
        event.setName("Test Event");
        event.setCreator(user);
        // ... initialize other fields of event
    }

    @Test
    public void createEventTest() {
        EventCreateRequest createRequest = new EventCreateRequest();

        // Populating createRequest with test data
        createRequest.setName("Test Event");
        createRequest.setDescription("This is a sample event description.");
        createRequest.setCategory(EventCategory.BASKETBALL); // Assuming EventCategory is an enum
        createRequest.setCoverImageUrl("http://example.com/event.jpg");
        createRequest.setEntryFee(10.0);
        createRequest.setLocation("Sample Location");
        createRequest.setTimestamp(LocalDateTime.now()); // Set current time as an example
        createRequest.setCreatorId(1); // Assuming a user with ID 1 exists

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventDetailResponse response = eventService.createEvent(createRequest);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(createRequest.getName());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void getAllEventsTest() {
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

        List<EventDetailResponse> responses = eventService.getAllEvents();

        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getName()).isEqualTo(event.getName());
    }

    @Test
    public void getEventByIdTest() {
        when(eventRepository.findById(anyInt())).thenReturn(Optional.of(event));

        EventDetailResponse response = eventService.getEventById(event.getId());

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(event.getName());
    }

    @Test
    public void updateEventTest() {
        EventUpdateRequest updateRequest = new EventUpdateRequest();
        // ... populate updateRequest with test data

        when(eventRepository.findById(anyInt())).thenReturn(Optional.of(event));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        EventDetailResponse response = eventService.updateEvent(event.getId(), updateRequest);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(updateRequest.getName());
        verify(eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    public void deleteEventTest() {
        doNothing().when(eventRepository).deleteById(anyInt());
        eventService.deleteEvent(event.getId());
        verify(eventRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void getEventsByLocationTest() {
        String location = "Test Location";
        when(eventRepository.findByLocation(location)).thenReturn(Arrays.asList(event));

        List<EventDetailResponse> responses = eventService.getEventsByLocation(location);

        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getLocation()).isEqualTo(event.getLocation());
    }
}
