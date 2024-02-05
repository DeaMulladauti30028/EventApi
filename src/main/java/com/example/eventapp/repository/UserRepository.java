package com.example.eventapp.repository;

import com.example.eventapp.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findByName(String name);



}
