package com.example.api_course_producer.repository;

import com.example.api_course_producer.model.course_model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
}
