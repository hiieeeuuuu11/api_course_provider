package com.example.api_course_producer.service.download;

import com.example.api_course_producer.entity.course.Course;
import java.util.List;


public interface CourseDownloadService {

    List<Course> getAllCourse();


    Course getCourseById(int course_id);

}
