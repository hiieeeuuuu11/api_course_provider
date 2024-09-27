package com.example.api_course_producer.service.download;

import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.service.cloud.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICourseDownloadService implements CourseDownloadService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    S3Service service;

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int course_id) {
        Course course = courseRepository.findById(course_id).orElse(null);
        return course;
    }

}
