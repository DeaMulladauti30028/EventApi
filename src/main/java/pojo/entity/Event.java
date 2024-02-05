package pojo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getEntryfee() {
        return entryfee;
    }

    public void setEntryfee(Double entryfee) {
        this.entryfee = entryfee;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }
}
