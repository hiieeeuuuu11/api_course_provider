package com.example.api_course_producer.repository;

import com.example.api_course_producer.entity.course.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter,Integer> {

    List<Chapter> findChaptersByCourse_Id(int course_id);

    Integer countChapterByCourse_Id(int course_id);

}
