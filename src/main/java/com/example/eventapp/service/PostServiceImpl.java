package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.*;
import com.example.eventapp.pojo.entity.Event;
import com.example.eventapp.pojo.entity.Post;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.PostRepository;
import com.example.eventapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(EventRepository eventRepository, UserRepository userRepository, PostRepository postRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /**
     * Create a new post based on the provided create request.
     * @param createRequest The post create request containing post content, image URL, author ID, and event ID.
     * @return A PostDetailResponse object with post details.
     * @throws RuntimeException if the author or event is not found.
     */
    @Override
    public PostDetailResponse createPost(PostCreateRequest createRequest) {
        // Create a new Post object and populate it with request data
        Post post = new Post();
        post.setContent(createRequest.getContent());
        post.setImageUrl(createRequest.getImageUrl());

        // Find the author user by ID, or throw an exception if not found
        User author = userRepository.findById(createRequest.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setAuthor(author);

        // Find the event by ID, or throw an exception if not found
        Event event = eventRepository.findById(createRequest.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));
        post.setEvent(event);

        // Save the post to the database
        Post savedPost = postRepository.save(post);

        // Convert the saved post to a PostDetailResponse object
        return convertToPostDetailResponse(savedPost);
    }

    /**
     * Convert a Post object to a PostDetailResponse object.
     * @param post The Post object to convert.
     * @return A PostDetailResponse object with post details.
     */
    private PostDetailResponse convertToPostDetailResponse(Post post) {
        PostDetailResponse response = new PostDetailResponse();
        response.setId(post.getId());
        response.setContent(post.getContent());
        response.setImageUrl(post.getImageUrl());
        response.setTimestamp(post.getTimestamp());

        if (post.getAuthor() != null) {
            response.setAuthor(new UserDTO(post.getAuthor().getUsername()));
        }
        if (post.getEvent() != null) {
            response.setEvent(new EventDTO(post.getEvent().getName()));
        }

        return response;
    }

    /**
     * Update an existing post based on the provided post ID and update request.
     * @param postId The ID of the post to be updated.
     * @param updateRequest The post update request containing updated content and image URL.
     * @return A PostDetailResponse object with updated post details.
     * @throws RuntimeException if the post is not found.
     */
    @Override
    public PostDetailResponse updatePost(Integer postId, PostUpdateRequest updateRequest) {
        // Find the post by its ID, or throw an exception if not found
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Update the post's content and image URL with the data from the update request
        post.setContent(updateRequest.getContent());
        post.setImageUrl(updateRequest.getImageUrl());

        // Do not change author and event

        // Save the updated post to the database
        Post updatedPost = postRepository.save(post);

        // Convert the updated post to a PostDetailResponse object
        return convertToPostDetailResponse(updatedPost);
    }

    /**
     * Delete a post based on the provided post ID.
     * @param postId The ID of the post to be deleted.
     */
    @Override
    public void deletePost(Integer postId) {
        // Delete the post from the database by ID
        postRepository.deleteById(postId);
    }

    /**
     * Retrieve a post by its ID.
     * @param postId The ID of the post to retrieve.
     * @return A PostDetailResponse object with the post's details.
     * @throws RuntimeException if the post is not found.
     */
    @Override
    public PostDetailResponse getPostById(Integer postId) {
        // Find the post by its ID, or throw an exception if not found
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return convertToPostDetailResponse(post);
    }

    /**
     * Retrieve a list of posts by the author's user ID.
     * @param authorId The ID of the author to retrieve posts for.
     * @return A list of PostDetailResponse objects with post details.
     */
    @Override
    public List<PostDetailResponse> getPostsByAuthor(Integer authorId) {
        // Find posts by author ID
        List<Post> posts = postRepository.findByAuthorId(authorId);

        // Convert the list of posts to a list of PostDetailResponse objects
        return posts.stream()
                .map(this::convertToPostDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a list of posts by the event's ID.
     * @param eventId The ID of the event to retrieve posts for.
     * @return A list of PostDetailResponse objects with post details.
     */
    @Override
    public List<PostDetailResponse> getPostsByEvent(Integer eventId) {
        // Find posts by event ID
        List<Post> posts = postRepository.findByEventId(eventId);

        // Convert the list of posts to a list of PostDetailResponse objects
        return posts.stream()
                .map(this::convertToPostDetailResponse)
                .collect(Collectors.toList());
    }
}
