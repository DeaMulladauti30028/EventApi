package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.EventCreateRequest;
import com.example.eventapp.pojo.dto.EventDetailResponse;
import com.example.eventapp.pojo.dto.EventUpdateRequest;
import com.example.eventapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class handles HTTP requests related to event operations.
 * It provides endpoints for creating, updating, retrieving, and deleting events.
 */
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Create a new event based on the provided event create request.
     * @param eventCreateRequest The event create request containing event details.
     * @return A ResponseEntity with the created EventDetailResponse and HTTP status code 200 (OK).
     */
    @PostMapping
    public ResponseEntity<EventDetailResponse> createEvent(@RequestBody EventCreateRequest eventCreateRequest) {
        EventDetailResponse createdEvent = eventService.createEvent(eventCreateRequest);
        return ResponseEntity.ok(createdEvent);
    }

    /**
     * Retrieve a list of all events.
     * @return A ResponseEntity with a list of EventDetailResponse objects and HTTP status code 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<EventDetailResponse>> getAllEvents() {
        List<EventDetailResponse> eventDetailResponses = eventService.getAllEvents();
        return ResponseEntity.ok(eventDetailResponses);
    }

    /**
     * Retrieve event information by event ID.
     * @param id The ID of the event to retrieve.
     * @return A ResponseEntity with the EventDetailResponse and HTTP status code 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventDetailResponse> getEventById(@PathVariable Integer id) {
        EventDetailResponse eventDetailResponse = eventService.getEventById(id);
        return ResponseEntity.ok(eventDetailResponse);
    }

    /**
     * Retrieve a list of events based on location.
     * @param location The location to search for events.
     * @return A ResponseEntity with a list of EventDetailResponse objects and HTTP status code 200 (OK).
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<List<EventDetailResponse>> getEventsByLocation(@PathVariable String location) {
        List<EventDetailResponse> eventDetailResponses = eventService.getEventsByLocation(location);
        return ResponseEntity.ok(eventDetailResponses);
    }

    /**
     * Update an existing event's information based on the provided event ID and update request.
     * @param id The ID of the event to be updated.
     * @param eventUpdateRequest The event update request containing updated event details.
     * @return A ResponseEntity with the updated EventDetailResponse and HTTP status code 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventDetailResponse> updateEvent(@PathVariable Integer id, @RequestBody EventUpdateRequest eventUpdateRequest) {
        EventDetailResponse updatedEventResponse = eventService.updateEvent(id, eventUpdateRequest);
        return ResponseEntity.ok(updatedEventResponse);
    }

    /**
     * Delete an event based on the provided event ID.
     * @param id The ID of the event to be deleted.
     * @return A ResponseEntity with no content and HTTP status code 200 (OK) upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}

