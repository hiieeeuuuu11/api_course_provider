package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.course.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter,Integer> {


   // List<Chapter> getChaptersByCourse_idOrderByPosition(int chapter_id);

}
