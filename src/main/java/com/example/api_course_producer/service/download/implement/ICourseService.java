package com.example.api_course_producer.service.download.implement;

import com.example.api_course_producer.model.course_model.Chapter;
import com.example.api_course_producer.model.course_model.Course;
import com.example.api_course_producer.repository.ChapterRepository;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.service.download.interface_service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICourseService implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(int course_id) {
        return null;
    }

    public List<Chapter> getAllChaptersFromCourse(int course_id){
        return chapterRepository.findAllByCourse_id(course_id);
    }

}
