package com.example.eventapp.pojo.dto;

import com.example.eventapp.pojo.entity.RSVPStatus;

public class AttendanceDetailResponse {
    private Integer id;
    private UserDTO user;
    private EventDTO event;
    private RSVPStatus rsvpStatus;

    public AttendanceDetailResponse(Integer id, UserDTO user, EventDTO event, RSVPStatus rsvpStatus) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.rsvpStatus = rsvpStatus;
    }

    public AttendanceDetailResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public RSVPStatus getRsvpStatus() {
        return rsvpStatus;
    }

    public void setRsvpStatus(RSVPStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }
}
