package pojo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "Post"
)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(
            name = "Content"
    )
    private String content;
    @Column(
            name = "ImageUrl"
    )
    private String imageUrl;
    @Column(
            name = "Timestamp"
    )
    private LocalDateTime timestamp;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id"
    )
    private User author;
    @ManyToOne
    private Event event;
    @ManyToMany
    private Set<User> likes = new HashSet();

    public Post() {
    }

    public Post(Integer id, String content, String imageUrl, LocalDateTime timestamp, User author, Event event, Set<User> likes) {
        this.id = id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.author = author;
        this.event = event;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }
}

