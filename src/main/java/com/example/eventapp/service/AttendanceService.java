package com.example.eventapp.service;

import com.example.eventapp.pojo.dto.AttendanceCreateRequest;
import com.example.eventapp.pojo.dto.AttendanceDetailResponse;
import com.example.eventapp.pojo.dto.AttendanceUpdateRequest;

import java.util.List;

public interface AttendanceService {
    AttendanceDetailResponse createAttendance(AttendanceCreateRequest request);
    AttendanceDetailResponse updateAttendance(Integer attendanceId, AttendanceUpdateRequest request);
    void deleteAttendance(Integer attendanceId);
    AttendanceDetailResponse getAttendanceById(Integer attendanceId);
    List<AttendanceDetailResponse> getAttendancesByUser(Integer userId);
    List<AttendanceDetailResponse> getAttendancesByEvent(Integer eventId);

}
