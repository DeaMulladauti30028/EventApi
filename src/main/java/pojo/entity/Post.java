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
    @OneToMany(
            mappedBy = "post"
    )
    private Set<Comment> comments = new HashSet();

    public Post() {
    }

    public Post(Integer id, String content, String imageUrl, LocalDateTime timestamp, User author) {
        this.id = id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.author = author;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<User> getLikes() {
        return this.likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}

