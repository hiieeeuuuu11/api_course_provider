package com.example.api_course_producer.service.third_party_service;

import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;

public interface ThirdParty_CourseService {

    Lesson giveLessonByIdToTPA(String token,int id);

    Course giveCourseByIdToTPA(String token,int id);

}
