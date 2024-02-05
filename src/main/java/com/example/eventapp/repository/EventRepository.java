package com.example.eventapp.repository;

import com.example.eventapp.pojo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findByLocation(String location);

}
