package com.example.api_course_producer.repository;

import com.example.api_course_producer.model.course_model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
}
