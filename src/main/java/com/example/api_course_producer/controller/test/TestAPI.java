package com.example.api_course_producer.controller.test;

import com.example.api_course_producer.model.third_party_app.ThirdParty_Course;
import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.repository.ThirdParty_CourseRepository;
import com.example.api_course_producer.service.cloud.S3Service;
import com.example.api_course_producer.service.third_party_service.ThirdPartyService;
import com.example.api_course_producer.service.token.JwtService;
import com.example.api_course_producer.service.upload.ChapterUploadService;
import com.example.api_course_producer.service.upload.LessonUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestAPI {

    @Autowired
    S3Service s3service;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterUploadService chapterUploadService;

    @Autowired
    LessonUploadService lessonUploadService;

    @Autowired
    JwtService jwtService;

    @Autowired
    ThirdParty_CourseRepository thirdParty_courseRepository;

    @Autowired
    ThirdPartyService thirdPartyService;



//    @GetMapping("/9")
//    public ResponseEntity<String> test9(@RequestBody LessonRequest lessonRequest) {
//        Lesson response = lessonUploadService.addLessonToChapter(lessonRequest);
//        return ResponseEntity.ok(response);
//    }


}
