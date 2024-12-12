package com.example.api_course_producer.repository;

import com.example.api_course_producer.dto.Top4Response;
import com.example.api_course_producer.entity.course.Chapter;
import com.example.api_course_producer.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {

  List<Course> findAllByProviderId(int providerId);

  Integer countByProviderId(int providerId);

  @Query("SELECT c.title, c.description, c.imageUrl, c.price, COUNT(e.id) AS registrationCount " +
      "FROM Course c " +
      "JOIN Enrollment e ON c.id = e.course.id " +
      "WHERE c.provider.id = :providerId " +
      "GROUP BY c.id, c.title, c.description, c.imageUrl, c.price " +
      "ORDER BY registrationCount DESC")
  List<Object[]> find4ByProvider(@Param("providerId") int providerId);

  ;
}