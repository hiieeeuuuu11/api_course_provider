package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.course.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
  List<Enrollment> getAllByCourseIdIn(List<Integer> courseIds);

  @Query(value = "SELECT l.username AS name, COUNT(e.id) AS course_count FROM Enrollment e JOIN User l ON e.learner.id = l.id GROUP BY l.id ORDER BY course_count DESC LIMIT 5")
  List<Object[]> findTopLearnersByEnrollmentCount();
}
