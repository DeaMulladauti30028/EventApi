package com.example.eventapp.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(
        name = "User"
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer Id;
    @Column(
            name = "Name"
    )
    private String name;
    @Column(
            name = "Username"
    )
    private String username;
    @Column(
            name = "Email"
    )
    private String email;
    @Column(
            name = "Password"
    )
    private String password;
    @Column(
            name = "Bio"
    )
    private String bio;
    @Column(
            name = "ProfilePictureUrl"
    )
    private String profilePictureUrl;
    @Column(
            name = "Location"
    )
    private String location;
    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = {@JoinColumn(
                    name = "followed_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "follower_id"
            )}
    )
    private Set<User> followers = new HashSet();

    @ManyToMany(
            mappedBy = "followers"
    )
    private Set<User> following = new HashSet();
    @OneToMany(
            mappedBy = "author",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private Set<Post> posts;
    @ManyToMany(
            mappedBy = "attendees"
    )
    private Set<Event> eventsAttended = new HashSet();
    @OneToMany(
            mappedBy = "creator"
    )
    private Set<Event> eventsCreated = new HashSet();
    @ManyToMany
    private Set<Post> likedPosts = new HashSet();
    @OneToMany(
            mappedBy = "author"
    )
    private Set<Comment> comments = new HashSet();
    @ManyToMany
    @JoinTable(
            name = "user_favorites",
            joinColumns = {@JoinColumn(
                    name = "user_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "event_id"
            )}
    )
    private Set<Event> favoriteEvents = new HashSet();

    public User() {
    }

    public User(Integer id, String name, String username, String email, String password, String bio, String profilePictureUrl, String location, Set<User> followers, Set<User> following, Set<Post> posts, Set<Event> eventsAttended, Set<Event> eventsCreated, Set<Post> likedPosts, Set<Comment> comments, Set<Event> favoriteEvents) {
        this.Id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.location = location;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.eventsAttended = eventsAttended;
        this.eventsCreated = eventsCreated;
        this.likedPosts = likedPosts;
        this.comments = comments;
        this.favoriteEvents = favoriteEvents;
    }

    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<User> getFollowers() {
        return this.followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return this.following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Set<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Event> getEventsAttended() {
        return this.eventsAttended;
    }

    public void setEventsAttended(Set<Event> eventsAttended) {
        this.eventsAttended = eventsAttended;
    }

    public Set<Event> getEventsCreated() {
        return this.eventsCreated;
    }

    public void setEventsCreated(Set<Event> eventsCreated) {
        this.eventsCreated = eventsCreated;
    }

    public Set<Post> getLikedPosts() {
        return this.likedPosts;
    }

    public void setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Event> getFavoriteEvents() {
        return this.favoriteEvents;
    }

    public void setFavoriteEvents(Set<Event> favoriteEvents) {
        this.favoriteEvents = favoriteEvents;
    }
}
