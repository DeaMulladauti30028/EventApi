package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.AttendanceCreateRequest;
import com.example.eventapp.pojo.dto.AttendanceDetailResponse;
import com.example.eventapp.pojo.dto.AttendanceUpdateRequest;
import com.example.eventapp.pojo.entity.Attendance;
import com.example.eventapp.pojo.entity.Event;
import com.example.eventapp.pojo.entity.RSVPStatus;
import com.example.eventapp.pojo.entity.User;
import com.example.eventapp.repository.AttendanceRepository;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceImplTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    private Attendance attendance;
    private User user;
    private Event event;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testUser");

        event = new Event();
        event.setId(1);
        event.setName("Sample Event");

        attendance = new Attendance();
        attendance.setId(1);
        attendance.setUser(user);
        attendance.setEvent(event);
        // Set other fields as needed
    }

    @Test
    public void createAttendanceTest() {
        AttendanceCreateRequest createRequest = new AttendanceCreateRequest();
        createRequest.setUserId(user.getId());
        createRequest.setEventId(event.getId());
        createRequest.setRsvpStatus(RSVPStatus.ATTENDING); // Set the RSVPStatus

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));
        when(attendanceRepository.save(any(Attendance.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AttendanceDetailResponse response = attendanceService.createAttendance(createRequest);

        assertThat(response).isNotNull();
        assertThat(response.getRsvpStatus()).isEqualTo(createRequest.getRsvpStatus());
        verify(attendanceRepository, times(1)).save(any(Attendance.class));
    }

    @Test
    public void updateAttendanceTest() {
        AttendanceUpdateRequest updateRequest = new AttendanceUpdateRequest();
        updateRequest.setRsvpStatus(RSVPStatus.NOT_ATTENDING); // Adjust as per your RSVPStatus enum

        when(attendanceRepository.findById(attendance.getId())).thenReturn(Optional.of(attendance));
        when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);

        AttendanceDetailResponse response = attendanceService.updateAttendance(attendance.getId(), updateRequest);

        assertThat(response).isNotNull();
        assertThat(response.getRsvpStatus()).isEqualTo(updateRequest.getRsvpStatus());
        verify(attendanceRepository, times(1)).save(any(Attendance.class));
    }

    @Test
    public void deleteAttendanceTest() {
        when(attendanceRepository.existsById(attendance.getId())).thenReturn(true);
        doNothing().when(attendanceRepository).deleteById(attendance.getId());

        attendanceService.deleteAttendance(attendance.getId());

        verify(attendanceRepository, times(1)).deleteById(attendance.getId());
    }

    @Test
    public void getAttendanceByIdTest() {
        when(attendanceRepository.findById(attendance.getId())).thenReturn(Optional.of(attendance));

        AttendanceDetailResponse response = attendanceService.getAttendanceById(attendance.getId());

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(attendance.getId());
        verify(attendanceRepository, times(1)).findById(attendance.getId());
    }

    @Test
    public void getAttendancesByUserTest() {
        when(attendanceRepository.findByUserId(user.getId())).thenReturn(Arrays.asList(attendance));

        List<AttendanceDetailResponse> responses = attendanceService.getAttendancesByUser(user.getId());

        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getUser().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void getAttendancesByEventTest() {
        when(attendanceRepository.findByEventId(event.getId())).thenReturn(Arrays.asList(attendance));

        List<AttendanceDetailResponse> responses = attendanceService.getAttendancesByEvent(event.getId());

        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getEvent().getName()).isEqualTo(event.getName());
    }
}
