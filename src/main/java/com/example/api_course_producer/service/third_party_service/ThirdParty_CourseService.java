package com.example.api_course_producer.service.third_party_service;

import com.example.api_course_producer.dto.TPACourseResponse;
import com.example.api_course_producer.dto.TPA_CourseRequest;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.model.third_party_app.ThirdPartyApplication;
import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;

import java.util.List;

public interface ThirdParty_CourseService {

    Lesson giveLessonByIdToTPA(String token,int course_id,int id);

    TPACourseResponse giveCourseByIdToTPA(String token, int id);

    ThirdParty_Course addTPACourse(TPA_CourseRequest tpa_course);

    String generateToken(int id);

    List<ThirdParty_Course> getTPAByStatus(int status);

    ThirdParty_Course find(int id);

}
