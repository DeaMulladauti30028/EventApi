package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.*;
import com.example.eventapp.pojo.entity.RSVPStatus;
import com.example.eventapp.service.AttendanceService;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AttendanceController.class)
public class AttendanceControllerTest {

    @MockBean
    private AttendanceService attendanceService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private AttendanceDetailResponse attendanceDetailResponse;

    @BeforeEach
    public void setUp() {
        attendanceDetailResponse = new AttendanceDetailResponse();
        attendanceDetailResponse.setId(1);
        attendanceDetailResponse.setUser(new UserDTO("testUser")); // Assuming UserDTO is properly set
        attendanceDetailResponse.setEvent(new EventDTO("testEvent")); // Assuming EventDTO is properly set
        attendanceDetailResponse.setRsvpStatus(RSVPStatus.ATTENDING); // Assuming RSVPStatus is an enum
    }

    @Test
    public void createAttendanceTest() throws Exception {
        AttendanceCreateRequest createRequest = new AttendanceCreateRequest();
        createRequest.setUserId(1);
        createRequest.setEventId(1);
        createRequest.setRsvpStatus(RSVPStatus.ATTENDING);

        when(attendanceService.createAttendance(any(AttendanceCreateRequest.class))).thenReturn(attendanceDetailResponse);

        mockMvc.perform(post("/attendances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rsvpStatus").value(RSVPStatus.ATTENDING.toString()));

        verify(attendanceService, times(1)).createAttendance(any(AttendanceCreateRequest.class));
    }


    @Test
    public void updateAttendanceTest() throws Exception {
        int attendanceId = 1;
        AttendanceUpdateRequest updateRequest = new AttendanceUpdateRequest();
        updateRequest.setRsvpStatus(RSVPStatus.NOT_ATTENDING);

        // Setting up the expected response with the updated RSVP status
        attendanceDetailResponse.setRsvpStatus(RSVPStatus.NOT_ATTENDING);

        when(attendanceService.updateAttendance(eq(attendanceId), any(AttendanceUpdateRequest.class)))
                .thenReturn(attendanceDetailResponse);

        mockMvc.perform(put("/attendances/{id}", attendanceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rsvpStatus").value(RSVPStatus.NOT_ATTENDING.toString()));

        verify(attendanceService, times(1)).updateAttendance(eq(attendanceId), any(AttendanceUpdateRequest.class));
    }

    @Test
    public void getAttendanceByIdTest() throws Exception {
        int attendanceId = 1;

        when(attendanceService.getAttendanceById(attendanceId)).thenReturn(attendanceDetailResponse);

        mockMvc.perform(get("/attendances/{id}", attendanceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(attendanceId));

        verify(attendanceService, times(1)).getAttendanceById(attendanceId);
    }

    @Test
    public void getAttendancesByUserTest() throws Exception {
        int userId = 1;
        int attendanceId = 1; // Define the expected attendance ID
        List<AttendanceDetailResponse> attendances = Arrays.asList(attendanceDetailResponse);

        // Mock the expected behavior
        when(attendanceService.getAttendancesByUser(userId)).thenReturn(attendances);

        // Perform the test
        mockMvc.perform(get("/attendances/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(attendanceId)); // Use the defined attendanceId for assertion

        // Verify the service method was called
        verify(attendanceService, times(1)).getAttendancesByUser(userId);
    }

    @Test
    public void getAttendancesByEventTest() throws Exception {
        int eventId = 1;
        int expectedAttendanceId = 1; // Define the expected attendance ID
        List<AttendanceDetailResponse> attendances = Arrays.asList(attendanceDetailResponse);

        // Mock the expected behavior of the service
        when(attendanceService.getAttendancesByEvent(eventId)).thenReturn(attendances);

        // Perform the mock HTTP GET request and verify the response
        mockMvc.perform(get("/attendances/event/{eventId}", eventId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(expectedAttendanceId)); // Validate against the expectedAttendanceId

        // Verify the service method was invoked
        verify(attendanceService, times(1)).getAttendancesByEvent(eventId);
    }


    @Test
    public void deleteAttendanceTest() throws Exception {
        int attendanceId = 1;

        doNothing().when(attendanceService).deleteAttendance(attendanceId);

        mockMvc.perform(delete("/attendances/{id}", attendanceId))
                .andExpect(status().isOk());

        verify(attendanceService, times(1)).deleteAttendance(attendanceId);
    }
}