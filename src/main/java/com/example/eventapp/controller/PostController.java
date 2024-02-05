package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.PostCreateRequest;
import com.example.eventapp.pojo.dto.PostDetailResponse;
import com.example.eventapp.pojo.dto.PostUpdateRequest;
import com.example.eventapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Create a new post based on the provided post create request.
     * @param createRequest The post create request containing post details.
     * @return A ResponseEntity with the created PostDetailResponse and HTTP status code 200 (OK).
     */
    @PostMapping
    public ResponseEntity<PostDetailResponse> createPost(@RequestBody PostCreateRequest createRequest) {
        PostDetailResponse newPost = postService.createPost(createRequest);
        return ResponseEntity.ok(newPost);
    }

    /**
     * Update an existing post's information based on the provided post ID and update request.
     * @param id The ID of the post to be updated.
     * @param updateRequest The post update request containing updated post details.
     * @return A ResponseEntity with the updated PostDetailResponse and HTTP status code 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDetailResponse> updatePost(@PathVariable Integer id, @RequestBody PostUpdateRequest updateRequest) {
        PostDetailResponse updatedPost = postService.updatePost(id, updateRequest);
        return ResponseEntity.ok(updatedPost);
    }

    /**
     * Retrieve post information by post ID.
     * @param id The ID of the post to retrieve.
     * @return A ResponseEntity with the PostDetailResponse and HTTP status code 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPostById(@PathVariable Integer id) {
        PostDetailResponse post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    /**
     * Retrieve a list of post information by the author's ID.
     * @param authorId The ID of the author to retrieve posts for.
     * @return A ResponseEntity with a list of PostDetailResponse objects and HTTP status code 200 (OK).
     */
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostDetailResponse>> getPostsByAuthor(@PathVariable Integer authorId) {
        List<PostDetailResponse> posts = postService.getPostsByAuthor(authorId);
        return ResponseEntity.ok(posts);
    }

    /**
     * Retrieve a list of post information by the event's ID.
     * @param eventId The ID of the event to retrieve posts for.
     * @return A ResponseEntity with a list of PostDetailResponse objects and HTTP status code 200 (OK).
     */
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<PostDetailResponse>> getPostsByEvent(@PathVariable Integer eventId) {
        List<PostDetailResponse> posts = postService.getPostsByEvent(eventId);
        return ResponseEntity.ok(posts);
    }

    /**
     * Delete a post based on the provided post ID.
     * @param id The ID of the post to be deleted.
     * @return A ResponseEntity with no content and HTTP status code 200 (OK) upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
