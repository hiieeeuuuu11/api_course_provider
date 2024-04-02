package com.example.api_course_producer.service.upload;

import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.model.course.Course;

public interface CourseUploadService {

    Course addCourse(Course course);

    Course updateCourse(Course course);

    Course addCourseDetailInformation(CourseRequest courseDetailInformation);

    Course updateCourseDetailInformation(CourseRequest courseDetailInformation);

}
