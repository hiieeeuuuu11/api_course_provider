package com.example.api_course_producer.service.course;

import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.entity.course.Course;
import java.util.List;

public interface CourseService {

    Course addCourse(Course course);

    Course updateCourse(Course course);

    Course addCourseDetailInformation(CourseRequest courseDetailInformation);

    Course updateCourseDetailInformation(CourseRequest courseDetailInformation);

    void delete(int id);

    List<Course> getAllCourse();


    Course getCourseById(int course_id);

}
