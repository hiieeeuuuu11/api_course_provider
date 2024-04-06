package com.example.api_course_producer.controller.download;

import com.example.api_course_producer.model.course.Course;
import com.example.api_course_producer.service.download.CourseDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/download/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    CourseDownloadService courseDownloadService;

    @GetMapping("/getallcourse")
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> listCourse = courseDownloadService.getAllCourse();
        return ResponseEntity.ok(listCourse);
    }

    @GetMapping("/getcoursebyid")
    public ResponseEntity<Course> getCourseById(@RequestParam("id") int id){
        Course course = courseDownloadService.getCourseById(id);
        return ResponseEntity.ok(course);
    }


}
