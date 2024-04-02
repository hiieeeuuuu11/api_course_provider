package com.example.api_course_producer.repository;

import com.example.api_course_producer.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {


}