package com.example.eventapp.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "Event"
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Event {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(
            name = "Name"
    )
    private String name;
    @Column(
            name = "Description"
    )
    private String description;
    @Column(
            name = "Timestamp"
    )
    private LocalDateTime timestamp;
    @Column(
            name = "Location"
    )
    private String location;
    @Column(
            name = "CoverImageUrl"
    )
    private String coverImageUrl;
    private Double entryfee;
    @Enumerated(EnumType.STRING)
    @Column(
            name = "Category"
    )
    private EventCategory category;
    @OneToMany(
            mappedBy = "event"
    )
    private Set<Post> posts = new HashSet();
    @ManyToMany
    private Set<User> attendees = new HashSet();
    @ManyToOne
    private User creator;
    @ManyToMany(
            mappedBy = "favoriteEvents"
    )
    private Set<User> favoritedByUsers = new HashSet();

    public Event() {
    }

    public Event(Integer id, String name, String description, LocalDateTime timestamp, String location, String coverImageUrl, Double entryfee, EventCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
        this.location = location;
        this.coverImageUrl = coverImageUrl;
        this.entryfee = entryfee;
        this.category = category;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoverImageUrl() {
        return this.coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Double getEntryfee() {
        return this.entryfee;
    }

    public void setEntryfee(Double entryfee) {
        this.entryfee = entryfee;
    }

    public EventCategory getCategory() {
        return this.category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public Set<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getAttendees() {
        return this.attendees;
    }

    public void setAttendees(Set<User> attendees) {
        this.attendees = attendees;
    }

    public User getCreator() {
        return this.creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getFavoritedByUsers() {
        return this.favoritedByUsers;
    }

    public void setFavoritedByUsers(Set<User> favoritedByUsers) {
        this.favoritedByUsers = favoritedByUsers;
    }
}
