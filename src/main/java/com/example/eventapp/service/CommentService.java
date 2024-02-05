package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.CommentCreateRequest;
import com.example.eventapp.pojo.dto.CommentDetailResponse;
import com.example.eventapp.pojo.dto.CommentUpdateRequest;

import java.util.List;

public interface CommentService {
    CommentDetailResponse createComment(CommentCreateRequest createRequest);
    CommentDetailResponse updateComment(Integer commentId, CommentUpdateRequest updateRequest);
    void deleteComment(Integer commentId);
    CommentDetailResponse getCommentById(Integer commentId);
    List<CommentDetailResponse> getCommentsByPost(Integer postId);
    List<CommentDetailResponse> getCommentsByAuthor(Integer authorId);


}