package com.example.eventapp.controller;

import com.example.eventapp.pojo.dto.AttendanceCreateRequest;
import com.example.eventapp.pojo.dto.AttendanceDetailResponse;
import com.example.eventapp.pojo.dto.AttendanceUpdateRequest;
import com.example.eventapp.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class handles HTTP requests related to attendance operations.
 * It provides endpoints for creating, updating, retrieving, and deleting attendance records.
 */
@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * Create a new attendance record based on the provided attendance create request.
     * @param request The attendance create request containing attendance details.
     * @return A ResponseEntity with the created AttendanceDetailResponse and HTTP status code 200 (OK).
     */
    @PostMapping
    public ResponseEntity<AttendanceDetailResponse> createAttendance(@RequestBody AttendanceCreateRequest request) {
        AttendanceDetailResponse newAttendance = attendanceService.createAttendance(request);
        return ResponseEntity.ok(newAttendance);
    }

    /**
     * Update an existing attendance record based on the provided attendance ID and update request.
     * @param id The ID of the attendance record to be updated.
     * @param request The attendance update request containing updated attendance details.
     * @return A ResponseEntity with the updated AttendanceDetailResponse and HTTP status code 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDetailResponse> updateAttendance(@PathVariable Integer id, @RequestBody AttendanceUpdateRequest request) {
        AttendanceDetailResponse updatedAttendance = attendanceService.updateAttendance(id, request);
        return ResponseEntity.ok(updatedAttendance);
    }

    /**
     * Retrieve attendance information by attendance ID.
     * @param id The ID of the attendance record to retrieve.
     * @return A ResponseEntity with the AttendanceDetailResponse and HTTP status code 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDetailResponse> getAttendanceById(@PathVariable Integer id) {
        AttendanceDetailResponse attendance = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

    /**
     * Retrieve a list of attendance records based on the associated user's ID.
     * @param userId The ID of the user to retrieve attendance records for.
     * @return A ResponseEntity with a list of AttendanceDetailResponse objects and HTTP status code 200 (OK).
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceDetailResponse>> getAttendancesByUser(@PathVariable Integer userId) {
        List<AttendanceDetailResponse> attendances = attendanceService.getAttendancesByUser(userId);
        return ResponseEntity.ok(attendances);
    }

    /**
     * Retrieve a list of attendance records based on the associated event's ID.
     * @param eventId The ID of the event to retrieve attendance records for.
     * @return A ResponseEntity with a list of AttendanceDetailResponse objects and HTTP status code 200 (OK).
     */
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<AttendanceDetailResponse>> getAttendancesByEvent(@PathVariable Integer eventId) {
        List<AttendanceDetailResponse> attendances = attendanceService.getAttendancesByEvent(eventId);
        return ResponseEntity.ok(attendances);
    }

    /**
     * Delete an attendance record based on the provided attendance ID.
     * @param id The ID of the attendance record to be deleted.
     * @return A ResponseEntity with no content and HTTP status code 200 (OK) upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Integer id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok().build();
    }
}