package com.example.api_course_producer.controller.upload;

import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.service.cloud.S3Service;
import com.example.api_course_producer.service.upload.CourseUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload/course")
public class CourseUploadController {

    @Autowired
    CourseUploadService courseUploadService;

    @Autowired
    S3Service s3service;

    @PostMapping("/add")
    public ResponseEntity<Course> add(@ModelAttribute CourseRequest courseDetailInformation) {
        String imgUrl = s3service.uploadFile(courseDetailInformation.getMultipartFile(),"logo");
        Course course = Course.builder()
                .id(courseDetailInformation.getId())
                .title(courseDetailInformation.getTitle())
                .description(courseDetailInformation.getDescription())
                .imageUrl(imgUrl)
                .isPublished(courseDetailInformation.getIsPublished())
                .build();
        courseUploadService.addCourse(course);
        return ResponseEntity.ok(course);
    }




}
