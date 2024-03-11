package com.example.api_course_producer.controller.download;

import com.example.api_course_producer.model.course_model.Chapter;
import com.example.api_course_producer.model.course_model.Course;
import com.example.api_course_producer.service.download.interface_service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/download")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/getallcourse")
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> listCourse = courseService.getAllCourse();
        return ResponseEntity.ok(listCourse);
    }

    @GetMapping("/getallchaptersbycourse")
    public ResponseEntity<List<Chapter>> getAllChaptersByCourse(@RequestParam("course") int course_id){
        List<Chapter> listChapters = courseService.getAllChaptersFromCourse(course_id);
        return ResponseEntity.ok(listChapters);
    }

}
