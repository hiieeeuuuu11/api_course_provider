package com.example.api_course_producer.service.download.interface_service;

import com.example.api_course_producer.model.course_model.Chapter;
import com.example.api_course_producer.model.course_model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> getAllCourse();

    List<Chapter> getAllChaptersFromCourse(int course_id);

    Course getCourse(int course_id);

}
