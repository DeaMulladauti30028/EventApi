package com.example.eventapp.repository;

import com.example.eventapp.pojo.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
    List<Attendance> findByUserId(Integer userId);
    List<Attendance> findByEventId(Integer eventId);

}
