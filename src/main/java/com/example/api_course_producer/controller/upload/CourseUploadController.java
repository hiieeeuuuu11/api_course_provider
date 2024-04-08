package com.example.api_course_producer.controller.upload;

import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.service.cloud.S3Service;
import com.example.api_course_producer.service.upload.CourseUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload/course")
@CrossOrigin("*")
public class CourseUploadController {

    @Autowired
    CourseUploadService courseUploadService;

    @Autowired
    S3Service s3service;

    @PostMapping("/add")
    public ResponseEntity<Course> add(@ModelAttribute CourseRequest courseDetailInformation) {
        Course course = courseUploadService.addCourseDetailInformation(courseDetailInformation);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/update")
    public ResponseEntity<Course> update(@ModelAttribute CourseRequest courseDetailInformation) {
        Course course = courseUploadService.updateCourseDetailInformation(courseDetailInformation);
        if(course!=null){
            return ResponseEntity.ok(course);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCourse(@RequestParam("id") int id) {
        courseUploadService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
