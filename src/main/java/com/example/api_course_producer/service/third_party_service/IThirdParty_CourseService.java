package com.example.api_course_producer.service.third_party_service;

import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.service.download.CourseDownloadService;
import com.example.api_course_producer.service.download.LessonDownloadService;
import com.example.api_course_producer.service.token.TPATokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IThirdParty_CourseService implements ThirdParty_CourseService{

    @Autowired
    CourseDownloadService courseDownloadService;

    @Autowired
    LessonDownloadService lessonDownloadService;

    @Autowired
    TPATokenService tpaTokenService;

    @Autowired
    ThirdParty_CourseService thirdParty_courseService;

    @Override
    public Course giveCourseByIdToTPA(String token,int id) {
        Boolean check = tpaTokenService.isTokenValid(token);
        Course course;
        if(check) {
            course = courseDownloadService.getCourseById(id);
            for(Chapter chapter : course.getChapters()){
                for(Lesson lesson : chapter.getLessons()){
                    lesson.setVideoUrl(null);
                    lesson.setTextUrl(null);
                }
            }
            return course;
        }else {
            return null;
        }
    }

    @Override
    public Lesson giveLessonByIdToTPA(String token,int id) {
        Boolean check = tpaTokenService.isTokenValid(token);
        Lesson lesson;
        if(check) {
            lesson= lessonDownloadService.getLessonbyId(id);
            return lesson;
        }else {
            return null;
        }
    }
}

