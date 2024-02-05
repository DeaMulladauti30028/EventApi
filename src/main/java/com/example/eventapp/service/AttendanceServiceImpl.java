package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.*;
import com.example.eventapp.pojo.entity.Attendance;
import com.example.eventapp.repository.AttendanceRepository;
import com.example.eventapp.repository.EventRepository;
import com.example.eventapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    /**
     * Create a new attendance record based on the provided attendance create request.
     * @param request The attendance create request containing user, event, and RSVP status.
     * @return An AttendanceDetailResponse object with attendance details.
     * @throws RuntimeException if the user or event is not found.
     */
    @Override
    public AttendanceDetailResponse createAttendance(AttendanceCreateRequest request) {
        Attendance attendance = new Attendance();

        // Find and set the user by ID, or throw an exception if not found
        attendance.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));

        // Find and set the event by ID, or throw an exception if not found
        attendance.setEvent(eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found")));

        attendance.setRsvpStatus(request.getRsvpStatus());

        // Save the attendance record to the database
        Attendance savedAttendance = attendanceRepository.save(attendance);

        // Convert the saved attendance to an AttendanceDetailResponse object
        return convertToAttendanceDetailResponse(savedAttendance);
    }

    /**
     * Convert an Attendance object to an AttendanceDetailResponse object.
     * @param attendance The Attendance object to convert.
     * @return An AttendanceDetailResponse object with attendance details.
     */
    private AttendanceDetailResponse convertToAttendanceDetailResponse(Attendance attendance) {
        AttendanceDetailResponse response = new AttendanceDetailResponse();
        response.setId(attendance.getId());
        response.setUser(new UserDTO(attendance.getUser().getUsername())); // Adjust according to UserDTO
        response.setEvent(new EventDTO(attendance.getEvent().getName())); // Adjust according to EventDTO
        response.setRsvpStatus(attendance.getRsvpStatus());
        return response;
    }

    /**
     * Update an existing attendance record based on the provided attendance ID and update request.
     * @param attendanceId The ID of the attendance record to be updated.
     * @param request The attendance update request containing updated RSVP status.
     * @return An AttendanceDetailResponse object with updated attendance details.
     * @throws RuntimeException if the attendance record is not found.
     */
    @Override
    public AttendanceDetailResponse updateAttendance(Integer attendanceId, AttendanceUpdateRequest request) {
        // Find the attendance record by its ID, or throw an exception if not found
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        // Update the RSVP status
        attendance.setRsvpStatus(request.getRsvpStatus());

        // Save the updated attendance record to the database
        Attendance updatedAttendance = attendanceRepository.save(attendance);

        // Convert the updated attendance to an AttendanceDetailResponse object
        return convertToAttendanceDetailResponse(updatedAttendance);
    }

    /**
     * Delete an attendance record based on the provided attendance ID.
     * @param attendanceId The ID of the attendance record to be deleted.
     * @throws RuntimeException if the attendance record is not found.
     */
    @Override
    public void deleteAttendance(Integer attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new RuntimeException("Attendance not found");
        }
        attendanceRepository.deleteById(attendanceId);
    }

    /**
     * Retrieve an attendance record by its ID.
     * @param attendanceId The ID of the attendance record to retrieve.
     * @return An AttendanceDetailResponse object with attendance details.
     * @throws RuntimeException if the attendance record is not found.
     */
    @Override
    public AttendanceDetailResponse getAttendanceById(Integer attendanceId) {
        // Find the attendance record by its ID, or throw an exception if not found
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return convertToAttendanceDetailResponse(attendance);
    }

    /**
     * Retrieve a list of attendance records by the user's ID.
     * @param userId The ID of the user to retrieve attendance records for.
     * @return A list of AttendanceDetailResponse objects with attendance details.
     */
    @Override
    public List<AttendanceDetailResponse> getAttendancesByUser(Integer userId) {
        // Find attendance records by the user's ID
        List<Attendance> attendances = attendanceRepository.findByUserId(userId);

        // Convert the list of attendance records to a list of AttendanceDetailResponse objects
        return attendances.stream()
                .map(this::convertToAttendanceDetailResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a list of attendance records by the event's ID.
     * @param eventId The ID of the event to retrieve attendance records for.
     * @return A list of AttendanceDetailResponse objects with attendance details.
     */
    @Override
    public List<AttendanceDetailResponse> getAttendancesByEvent(Integer eventId) {
        // Find attendance records by the event's ID
        List<Attendance> attendances = attendanceRepository.findByEventId(eventId);

        // Convert the list of attendance records to a list of AttendanceDetailResponse objects
        return attendances.stream()
                .map(this::convertToAttendanceDetailResponse)
                .collect(Collectors.toList());
    }
}
