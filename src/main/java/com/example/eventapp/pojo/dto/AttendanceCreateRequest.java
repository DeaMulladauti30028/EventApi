package com.example.eventapp.pojo.dto;

import com.example.eventapp.pojo.entity.RSVPStatus;

public class AttendanceCreateRequest {

    private Integer userId;
    private Integer eventId;
    private RSVPStatus rsvpStatus;

    public AttendanceCreateRequest(Integer userId, Integer eventId, RSVPStatus rsvpStatus) {
        this.userId = userId;
        this.eventId = eventId;
        this.rsvpStatus = rsvpStatus;
    }

    public AttendanceCreateRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public RSVPStatus getRsvpStatus() {
        return rsvpStatus;
    }

    public void setRsvpStatus(RSVPStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }
}