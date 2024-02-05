package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.UserDetailResponse;
import com.example.eventapp.pojo.dto.UserRegistrationRequest;
import com.example.eventapp.pojo.dto.UserUpdateRequest;
import com.example.eventapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDetailResponse userDetailResponse;

    @BeforeEach
    public void setUp() {
        userDetailResponse = new UserDetailResponse();
        // Populate userDetailResponse with test data
    }

    @Test
    public void createUserTest() throws Exception {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
        // Populate registrationRequest with test data

        when(userService.createUser(any(UserRegistrationRequest.class))).thenReturn(userDetailResponse);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(userDetailResponse.getName())));

        verify(userService, times(1)).createUser(any(UserRegistrationRequest.class));
    }

    @Test
    public void updateUserTest() throws Exception {
        UserUpdateRequest updateRequest = new UserUpdateRequest();
        // Populate updateRequest with test data
        int userId = 1;

        when(userService.updateUser(eq(userId), any(UserUpdateRequest.class))).thenReturn(userDetailResponse);

        mockMvc.perform(put("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(userDetailResponse.getName())));

        verify(userService, times(1)).updateUser(eq(userId), any(UserUpdateRequest.class));
    }

    @Test
    public void getUserByIdTest() throws Exception {
        int userId = 1;

        when(userService.getUserById(userId)).thenReturn(userDetailResponse);

        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(userDetailResponse.getName())));

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void findByUsernameTest() throws Exception {
        String username = "testUser";

        when(userService.findByUsername(username)).thenReturn(userDetailResponse);

        mockMvc.perform(get("/users/username/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(userDetailResponse.getUsername())));

        verify(userService, times(1)).findByUsername(username);
    }

    @Test
    public void findByEmailTest() throws Exception {
        String email = "test@example.com";

        when(userService.findByEmail(email)).thenReturn(userDetailResponse);

        mockMvc.perform(get("/users/email/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(userDetailResponse.getEmail())));

        verify(userService, times(1)).findByEmail(email);
    }

    @Test
    public void deleteUserTest() throws Exception {
        int userId = 1;

        doNothing().when(userService).deleteUser(userId);

        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(userId);
    }

    // Additional tests for other endpoints as required
}
