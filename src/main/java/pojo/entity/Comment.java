package pojo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "Comment"
)
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(
            name = "Content",
            nullable = false
    )
    private String content;
    @Column(
            name = "Timestamp"
    )
    private LocalDateTime timestamp;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private User author;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Post post;

    public Comment() {
    }

    public Comment(Integer id, String content, User author, Post post) {
        this.id = id;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.author = author;
        this.post = post;
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

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @PrePersist
    public void prePersist() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }

    }
}
