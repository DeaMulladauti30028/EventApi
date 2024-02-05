package com.example.eventapp.pojo.dto;

import com.example.eventapp.pojo.entity.EventCategory;

import java.time.LocalDateTime;

public class EventCreateRequest {
    private String name;
    private Integer creatorId;
    private String description;
    private LocalDateTime timestamp;
    private String location;
    private String coverImageUrl;
    private Double entryFee;
    private EventCategory category;

    public EventCreateRequest() {
    }

    public EventCreateRequest(String name,Integer creatorId, String description, LocalDateTime timestamp, String location, String coverImageUrl, Double entryFee, EventCategory category) {
        this.name = name;
        this.creatorId=creatorId;
        this.description = description;
        this.timestamp = timestamp;
        this.location = location;
        this.coverImageUrl = coverImageUrl;
        this.entryFee = entryFee;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Double entryFee) {
        this.entryFee = entryFee;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }
}
