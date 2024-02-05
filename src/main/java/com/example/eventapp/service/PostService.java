package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.PostCreateRequest;
import com.example.eventapp.pojo.dto.PostDetailResponse;
import com.example.eventapp.pojo.dto.PostUpdateRequest;

import java.util.List;

public interface PostService {

    PostDetailResponse createPost(PostCreateRequest createRequest);
    PostDetailResponse updatePost(Integer postId, PostUpdateRequest updateRequest);
    void deletePost(Integer postId);
    PostDetailResponse getPostById(Integer postId);
    List<PostDetailResponse> getPostsByAuthor(Integer authorId);
    List<PostDetailResponse> getPostsByEvent(Integer eventId);


}
