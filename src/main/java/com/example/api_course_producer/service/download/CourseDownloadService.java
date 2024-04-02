package com.example.api_course_producer.service.download;

import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.model.course.Course;
import java.util.List;


public interface CourseDownloadService {

    List<Course> getAllCourse();


    Course getCourseById(int course_id);

}
