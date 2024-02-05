package com.example.eventapp.service;

import com.example.eventapp.exceptions.ResourceNotFoundException;
import com.example.eventapp.pojo.dto.UserRegistrationRequest;
import com.example.eventapp.pojo.dto.UserUpdateRequest;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setName("Test User");
        // set other fields
    }

    @Test
    public void createUserTest() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        // Populate request with test data

        when(userRepository.save(any(User.class))).thenReturn(user);

        var response = userService.createUser(request);

        assertThat(response).isNotNull();
        assertThat(response.getUsername()).isEqualTo(request.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUserTest() {
        UserUpdateRequest request = new UserUpdateRequest();
        // Populate request with test data

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        var response = userService.updateUser(user.getId(), request);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(request.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void getUserByIdTest() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        var response = userService.getUserById(user.getId());

        assertThat(response).isNotNull();
        assertThat(response.getUsername()).isEqualTo(user.getUsername());
        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    public void findByUsernameTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        var response = userService.findByUsername(user.getUsername());

        assertThat(response).isNotNull();
        assertThat(response.getUsername()).isEqualTo(user.getUsername());
        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userRepository).deleteById(anyInt());
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void findByEmailTest() {
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        var response = userService.findByEmail(user.getEmail());

        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(user.getEmail());
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    public void getUserByIdNotFoundTest() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        try {
            userService.getUserById(user.getId());
        } catch (ResourceNotFoundException ex) {
            assertThat(ex).isInstanceOf(ResourceNotFoundException.class);
        }

        verify(userRepository, times(1)).findById(anyInt());
    }
}