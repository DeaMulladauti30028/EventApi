package com.example.eventapp.pojo.dto;

public class CommentCreateRequest {
    private String content;
    private Integer authorId;
    private Integer postId;

    public CommentCreateRequest() {
    }

    public CommentCreateRequest(String content, Integer authorId, Integer postId) {
        this.content = content;
        this.authorId = authorId;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
