package com.example.eventapp.service;

import com.example.eventapp.exceptions.ResourceNotFoundException;
import com.example.eventapp.pojo.dto.UserDetailResponse;
import com.example.eventapp.pojo.dto.UserRegistrationRequest;
import com.example.eventapp.pojo.dto.UserUpdateRequest;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new user based on the provided registration request.
     * @param request The user registration request containing user details.
     * @return A UserDetailResponse object with user information.
     */
    @Override
    public UserDetailResponse createUser(UserRegistrationRequest request) {
        // Create a new User object and populate it with request data
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setBio(request.getBio());
        user.setLocation(request.getLocation());
        user.setProfilePictureUrl(request.getProfilePictureUrl());

        // Save the user to the database
        User savedUser = userRepository.save(user);

        // Create a UserDetailResponse with the user's information
        UserDetailResponse response = new UserDetailResponse(
                request.getName(),
                request.getUsername(),
                request.getEmail(),
                request.getBio(),
                request.getProfilePictureUrl(),
                request.getLocation(),
                0,
                0);
        return response;
    }

    /**
     * Update an existing user's information based on the provided user ID and update request.
     * @param userId The ID of the user to be updated.
     * @param request The user update request containing updated user details.
     * @return A UserDetailResponse object with updated user information.
     * @throws ResourceNotFoundException if the user with the given ID is not found.
     */
    @Override
    public UserDetailResponse updateUser(Integer userId, UserUpdateRequest request) {
        // Find the user by their ID, or throw an exception if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Update the user's information with the data from the update request
        user.setName(request.getName());
        user.setBio(request.getBio());
        user.setProfilePictureUrl(request.getProfilePictureUrl());
        user.setLocation(request.getLocation());

        // Save the updated user to the database
        User updatedUser = userRepository.save(user);

        // Create a UserDetailResponse with the updated user's information
        UserDetailResponse response = new UserDetailResponse(
                request.getName(),
                user.getUsername(),
                user.getEmail(),
                request.getBio(),
                request.getProfilePictureUrl(),
                request.getLocation(),
                user.getFollowers().size(),
                user.getFollowing().size()
        );

        return response;
    }

    /**
     * Retrieve user information by user ID.
     * @param userId The ID of the user to retrieve.
     * @return A UserDetailResponse object with the user's information.
     * @throws ResourceNotFoundException if the user with the given ID is not found.
     */
    @Override
    public UserDetailResponse getUserById(Integer userId) {
        // Find the user by their ID, or throw an exception if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return getUserDetailResponse(user);
    }

    /**
     * Retrieve user information by username.
     * @param username The username of the user to retrieve.
     * @return A UserDetailResponse object with the user's information.
     * @throws ResourceNotFoundException if the user with the given username is not found.
     */
    @Override
    public UserDetailResponse findByUsername(String username) {
        // Attempt to find the user by their username, or throw an exception if not found
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(username));
        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return getUserDetailResponse(user);
    }

    // Helper method to create a UserDetailResponse from a User object
    private UserDetailResponse getUserDetailResponse(User user) {
        UserDetailResponse response = new UserDetailResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setBio(user.getBio());
        response.setLocation(user.getLocation());
        response.setProfilePictureUrl(user.getProfilePictureUrl());
        response.setFollowerCount(user.getFollowers().size());
        response.setFollowingCount(user.getFollowing().size());

        return response;
    }

    /**
     * Retrieve user information by email address.
     * @param email The email address of the user to retrieve.
     * @return A UserDetailResponse object with the user's information.
     * @throws ResourceNotFoundException if the user with the given email address is not found.
     */
    @Override
    public UserDetailResponse findByEmail(String email) {
        // Attempt to find the user by their email address, or throw an exception if not found
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return getUserDetailResponse(user);
    }

    /**
     * Delete a user based on the provided user ID.
     * @param userId The ID of the user to be deleted.
     */
    @Override
    public void deleteUser(Integer userId) {
        // Delete the user from the database by ID
        userRepository.deleteById(userId);
    }
}
