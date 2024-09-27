package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Integer> {

    @Query(value = "select u from AppUser u where u.username = :username and u.password=:password")
    Optional<AppUser> findAppUserByUsernameAndPassword(String username, String password);

    Optional<AppUser> findAppUserByUsername(String username);

}
