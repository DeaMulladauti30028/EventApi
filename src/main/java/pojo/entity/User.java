package pojo.entity;

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


    public User() {
    }

    public User(Integer id, String name, String username, String email, String password, String bio, String profilePictureUrl, String location, Set<User> followers) {
        Id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.location = location;
        this.followers = followers;
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }
}
