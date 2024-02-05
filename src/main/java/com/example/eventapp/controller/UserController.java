package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.UserDetailResponse;
import com.example.eventapp.pojo.dto.UserRegistrationRequest;
import com.example.eventapp.pojo.dto.UserUpdateRequest;
import com.example.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller class handles HTTP requests related to user operations.
 * It provides endpoints for creating, updating, retrieving, and deleting user information.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create a new user based on the provided user registration request.
     * @param request The user registration request containing user details.
     * @return A ResponseEntity with the created UserDetailResponse and HTTP status code 201 (Created).
     */
    @PostMapping
    public ResponseEntity<UserDetailResponse> createUser(@RequestBody UserRegistrationRequest request) {
        UserDetailResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Update an existing user's information based on the provided user ID and update request.
     * @param userId The ID of the user to be updated.
     * @param request The user update request containing updated user details.
     * @return A ResponseEntity with the updated UserDetailResponse and HTTP status code 200 (OK).
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDetailResponse> updateUser(@PathVariable Integer userId, @RequestBody UserUpdateRequest request) {
        UserDetailResponse response = userService.updateUser(userId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieve user information by user ID.
     * @param userId The ID of the user to retrieve.
     * @return A ResponseEntity with the UserDetailResponse and HTTP status code 200 (OK).
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailResponse> getUserById(@PathVariable Integer userId) {
        UserDetailResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieve user information by username.
     * @param username The username of the user to retrieve.
     * @return A ResponseEntity with the UserDetailResponse and HTTP status code 200 (OK).
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDetailResponse> findByUsername(@PathVariable String username) {
        UserDetailResponse response = userService.findByUsername(username);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieve user information by email.
     * @param email The email of the user to retrieve.
     * @return A ResponseEntity with the UserDetailResponse and HTTP status code 200 (OK).
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDetailResponse> findByEmail(@PathVariable String email) {
        UserDetailResponse response = userService.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a user based on the provided user ID.
     * @param userId The ID of the user to be deleted.
     * @return A ResponseEntity with no content and HTTP status code 204 (No Content) upon successful deletion.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
