package com.example.api_course_producer.repository;

import com.example.api_course_producer.model.course_model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter,Integer> {

    List<Chapter> findAllByCourse_id(int course_id);
}
