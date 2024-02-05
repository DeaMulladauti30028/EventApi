package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.EventCreateRequest;
import com.example.eventapp.pojo.dto.EventDetailResponse;
import com.example.eventapp.pojo.dto.EventUpdateRequest;

import java.util.List;

public interface EventService {

    EventDetailResponse createEvent(EventCreateRequest eventCreateRequest);
    List<EventDetailResponse> getAllEvents();
    EventDetailResponse getEventById(Integer eventId);
    List<EventDetailResponse> getEventsByLocation(String location);
    EventDetailResponse updateEvent(Integer eventId, EventUpdateRequest eventUpdateRequest);
    void deleteEvent(Integer eventId);
}