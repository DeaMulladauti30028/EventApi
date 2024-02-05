package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.EventCreateRequest;
import com.example.eventapp.pojo.dto.EventDetailResponse;
import com.example.eventapp.pojo.dto.EventUpdateRequest;
import com.example.eventapp.pojo.entity.Event;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a new event based on the provided event create request.
     * @param eventCreateRequest The event create request containing event details.
     * @return An EventDetailResponse object with event details.
     * @throws RuntimeException if the creator user is not found.
     */
    @Override
    public EventDetailResponse createEvent(EventCreateRequest eventCreateRequest) {
        // Create a new Event object and populate it with request data
        Event event = new Event();
        event.setName(eventCreateRequest.getName());
        event.setDescription(eventCreateRequest.getDescription());
        event.setCategory(eventCreateRequest.getCategory());
        event.setCoverImageUrl(eventCreateRequest.getCoverImageUrl());
        event.setEntryfee(eventCreateRequest.getEntryFee());
        event.setLocation(eventCreateRequest.getLocation());
        event.setTimestamp(eventCreateRequest.getTimestamp());

        // Find the creator user by ID, or throw an exception if not found
        User creator = userRepository.findById(eventCreateRequest.getCreatorId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        event.setCreator(creator);

        // Save the event to the database
        Event savedEvent = eventRepository.save(event);

        // Convert the saved event to an EventDetailResponse object
        return convertToEventDetailResponse(savedEvent);
    }

    /**
     * Convert an Event object to an EventDetailResponse object.
     * @param event The Event object to convert.
     * @return An EventDetailResponse object with event details.
     */
    private EventDetailResponse convertToEventDetailResponse(Event event) {
        EventDetailResponse response = new EventDetailResponse();
        response.setName(event.getName());
        response.setDescription(event.getDescription());
        response.setTimestamp(event.getTimestamp());
        response.setLocation(event.getLocation());
        response.setCoverImageUrl(event.getCoverImageUrl());
        response.setEntryFee(event.getEntryfee());
        response.setCategory(event.getCategory());
        // Map other fields if necessary

        return response;
    }

    /**
     * Retrieve a list of all events.
     * @return A list of EventDetailResponse objects with event details.
     */
    @Override
    public List<EventDetailResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();

        // Convert the list of events to a list of EventDetailResponse objects
        return events.stream()
                .map(this::convertToEventDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve an event by its ID.
     * @param eventId The ID of the event to retrieve.
     * @return An EventDetailResponse object with event details.
     * @throws RuntimeException if the event is not found.
     */
    @Override
    public EventDetailResponse getEventById(Integer eventId) {
        // Find the event by its ID, or throw an exception if not found
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return convertToEventDetailResponse(event);
    }

    /**
     * Retrieve a list of events by their location.
     * @param location The location of the events to retrieve.
     * @return A list of EventDetailResponse objects with event details.
     */
    @Override
    public List<EventDetailResponse> getEventsByLocation(String location) {
        List<Event> events = eventRepository.findByLocation(location);

        // Convert the list of events to a list of EventDetailResponse objects
        return events.stream()
                .map(this::convertToEventDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Update an existing event based on the provided event ID and update request.
     * @param eventId The ID of the event to be updated.
     * @param eventUpdateRequest The event update request containing updated event details.
     * @return An EventDetailResponse object with updated event details.
     * @throws RuntimeException if the event is not found.
     */
    @Override
    public EventDetailResponse updateEvent(Integer eventId, EventUpdateRequest eventUpdateRequest) {
        // Find the event by its ID, or throw an exception if not found
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Update the event's details with the data from the update request
        event.setName(eventUpdateRequest.getName());
        event.setDescription(eventUpdateRequest.getDescription());
        event.setCategory(eventUpdateRequest.getCategory());
        event.setCoverImageUrl(eventUpdateRequest.getCoverImageUrl());
        event.setEntryfee(eventUpdateRequest.getEntryFee());
        event.setLocation(eventUpdateRequest.getLocation());
        event.setTimestamp(eventUpdateRequest.getTimestamp());

        // Save the updated event to the database
        Event updatedEvent = eventRepository.save(event);

        // Convert the updated event to an EventDetailResponse object
        return convertToEventDetailResponse(updatedEvent);
    }

    /**
     * Delete an event based on the provided event ID.
     * @param eventId The ID of the event to be deleted.
     */
    @Override
    public void deleteEvent(Integer eventId) {
        // Delete the event from the database by ID
        eventRepository.deleteById(eventId);
    }
}