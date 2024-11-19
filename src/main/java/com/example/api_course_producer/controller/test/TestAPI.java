package com.example.api_course_producer.controller.test;

import com.example.api_course_producer.repository.CourseRepository;
import com.example.api_course_producer.service.cloud.S3Service;
import com.example.api_course_producer.service.token.JwtService;
import com.example.api_course_producer.service.course.ChapterService;
import com.example.api_course_producer.service.course.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
@CrossOrigin("*")
public class TestAPI {

    @Autowired
    S3Service s3service;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterService chapterUploadService;

    @Autowired
    LessonService lessonUploadService;

    @Autowired
    JwtService jwtService;

//    @GetMapping("/9")
//    public ResponseEntity<String> test9(@RequestBody LessonRequest lessonRequest) {
//        Lesson response = lessonUploadService.addLessonToChapter(lessonRequest);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/sends3")
    public String sendS3(@RequestParam("file") MultipartFile file) {
        return s3service.uploadFile(file, "img");
    }

}
