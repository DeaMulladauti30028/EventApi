package com.example.eventapp.pojo.entity;

import jakarta.persistence.*;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;
    @Enumerated(EnumType.STRING)
    private RSVPStatus rsvpStatus;

    public Attendance() {
    }

    public Attendance(Integer id, User user, Event event, RSVPStatus rsvpStatus) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.rsvpStatus = rsvpStatus;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public RSVPStatus getRsvpStatus() {
        return this.rsvpStatus;
    }

    public void setRsvpStatus(RSVPStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }
}
