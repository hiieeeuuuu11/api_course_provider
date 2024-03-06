package com.example.api_course_producer.repository;

import com.example.api_course_producer.model.course_model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
