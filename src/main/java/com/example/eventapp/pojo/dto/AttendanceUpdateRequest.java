package com.example.eventapp.pojo.dto;

import com.example.eventapp.pojo.entity.RSVPStatus;

public class AttendanceUpdateRequest {
    public RSVPStatus rsvpStatus;

    public AttendanceUpdateRequest(RSVPStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }

    public AttendanceUpdateRequest() {
    }

    public RSVPStatus getRsvpStatus() {
        return rsvpStatus;
    }

    public void setRsvpStatus(RSVPStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }
}