package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.UserDetailResponse;
import com.example.eventapp.pojo.dto.UserRegistrationRequest;
import com.example.eventapp.pojo.dto.UserUpdateRequest;

public interface UserService {

    UserDetailResponse createUser(UserRegistrationRequest request);
    UserDetailResponse updateUser(Integer userId, UserUpdateRequest request);
    UserDetailResponse getUserById(Integer userId);
    UserDetailResponse findByUsername(String username);
    UserDetailResponse findByEmail(String email);
    void deleteUser(Integer userId);
}
