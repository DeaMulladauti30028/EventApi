package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.EventCreateRequest;
import com.example.eventapp.pojo.dto.EventDetailResponse;
import com.example.eventapp.pojo.dto.EventUpdateRequest;
import com.example.eventapp.pojo.entity.EventCategory;
import com.example.eventapp.service.EventService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @MockBean
    private EventService eventService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private EventDetailResponse eventDetailResponse;

    @BeforeEach
    public void setUp() {
        eventDetailResponse = new EventDetailResponse();

        // Populating eventDetailResponse with test data
        eventDetailResponse.setName("Test Event");
        eventDetailResponse.setDescription("This is a test description for the event.");
        eventDetailResponse.setTimestamp(LocalDateTime.of(2022, 8, 15, 14, 30)); // Example date and time
        eventDetailResponse.setLocation("Test Location");
        eventDetailResponse.setCoverImageUrl("http://example.com/test-event.jpg");
        eventDetailResponse.setEntryFee(20.0);
        eventDetailResponse.setCategory(EventCategory.CONCERT); // Assuming CONCERT is a valid enum value
    }


    @Test
    public void createEventTest() throws Exception {
        // Creating EventCreateRequest with test data
        EventCreateRequest eventCreateRequest = new EventCreateRequest();
        eventCreateRequest.setName("Sample Event");
        eventCreateRequest.setCreatorId(1); // Assuming a user with ID 1 exists as the creator
        eventCreateRequest.setDescription("This is a sample event.");
        eventCreateRequest.setTimestamp(LocalDateTime.now()); // Use current time as an example
        eventCreateRequest.setLocation("Sample Location");
        eventCreateRequest.setCoverImageUrl("http://example.com/event.jpg");
        eventCreateRequest.setEntryFee(10.0);
        eventCreateRequest.setCategory(EventCategory.BASKETBALL); // Assuming EventCategory is an enum

        // Setting up the expected EventDetailResponse
        EventDetailResponse eventDetailResponse = new EventDetailResponse();
        eventDetailResponse.setName("Sample Event");
        eventDetailResponse.setDescription("This is a sample event.");
        eventDetailResponse.setTimestamp(LocalDateTime.now());
        eventDetailResponse.setLocation("Sample Location");
        eventDetailResponse.setCoverImageUrl("http://example.com/event.jpg");
        eventDetailResponse.setEntryFee(10.0);
        eventDetailResponse.setCategory(EventCategory.BASKETBALL);

        when(eventService.createEvent(any(EventCreateRequest.class))).thenReturn(eventDetailResponse);

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventCreateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample Event"))
                .andExpect(jsonPath("$.description").value("This is a sample event."))
                .andExpect(jsonPath("$.location").value("Sample Location"))
                .andExpect(jsonPath("$.coverImageUrl").value("http://example.com/event.jpg"))
                .andExpect(jsonPath("$.entryFee").value(10.0))
                .andExpect(jsonPath("$.category").value(EventCategory.BASKETBALL.toString()));

        verify(eventService, times(1)).createEvent(any(EventCreateRequest.class));
    }

    @Test
    public void getAllEventsTest() throws Exception {
        List<EventDetailResponse> eventResponses = Arrays.asList(eventDetailResponse);

        when(eventService.getAllEvents()).thenReturn(eventResponses);

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(eventDetailResponse.getName()));

        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    public void getEventByIdTest() throws Exception {
        int eventId = 1;

        when(eventService.getEventById(eventId)).thenReturn(eventDetailResponse);

        mockMvc.perform(get("/events/{id}", eventId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(eventDetailResponse.getName()));

        verify(eventService, times(1)).getEventById(eventId);
    }

    @Test
    public void getEventsByLocationTest() throws Exception {
        String location = "Test Location";
        List<EventDetailResponse> eventResponses = Arrays.asList(eventDetailResponse);

        when(eventService.getEventsByLocation(location)).thenReturn(eventResponses);

        mockMvc.perform(get("/events/location/{location}", location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(eventDetailResponse.getName()));

        verify(eventService, times(1)).getEventsByLocation(location);
    }

    @Test
    public void updateEventTest() throws Exception {
        int eventId = 1;
        // Creating EventUpdateRequest with test data
        EventUpdateRequest eventUpdateRequest = new EventUpdateRequest();
        eventUpdateRequest.setName("Updated Event Name");
        eventUpdateRequest.setDescription("Updated description.");
        eventUpdateRequest.setTimestamp(LocalDateTime.of(2023, 1, 1, 12, 0)); // Example date and time
        eventUpdateRequest.setLocation("Updated Location");
        eventUpdateRequest.setCoverImageUrl("http://example.com/updated-event.jpg");
        eventUpdateRequest.setEntryFee(25.0);
        eventUpdateRequest.setCategory(EventCategory.CONCERT); // Assuming CONCERT is a valid enum value

        // Setting up the expected EventDetailResponse after update
        eventDetailResponse.setName(eventUpdateRequest.getName());
        eventDetailResponse.setDescription(eventUpdateRequest.getDescription());
        eventDetailResponse.setTimestamp(eventUpdateRequest.getTimestamp());
        eventDetailResponse.setLocation(eventUpdateRequest.getLocation());
        eventDetailResponse.setCoverImageUrl(eventUpdateRequest.getCoverImageUrl());
        eventDetailResponse.setEntryFee(eventUpdateRequest.getEntryFee());
        eventDetailResponse.setCategory(eventUpdateRequest.getCategory());

        when(eventService.updateEvent(eq(eventId), any(EventUpdateRequest.class))).thenReturn(eventDetailResponse);

        mockMvc.perform(put("/events/{id}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventUpdateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Event Name"))
                .andExpect(jsonPath("$.description").value("Updated description."))
                .andExpect(jsonPath("$.location").value("Updated Location"))
                .andExpect(jsonPath("$.coverImageUrl").value("http://example.com/updated-event.jpg"))
                .andExpect(jsonPath("$.entryFee").value(25.0))
                .andExpect(jsonPath("$.category").value(EventCategory.CONCERT.toString()));

        verify(eventService, times(1)).updateEvent(eq(eventId), any(EventUpdateRequest.class));
    }

    @Test
    public void deleteEventTest() throws Exception {
        int eventId = 1;

        doNothing().when(eventService).deleteEvent(eventId);

        mockMvc.perform(delete("/events/{id}", eventId))
                .andExpect(status().isOk());

        verify(eventService, times(1)).deleteEvent(eventId);
    }
}
