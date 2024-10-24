package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
    List<Lesson> findLessonsByChapter_Id(int chapter_id);
}
