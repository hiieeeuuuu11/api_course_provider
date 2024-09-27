package com.example.api_course_producer.controller.course;

import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.service.course.CourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload/course")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<Course> add(@ModelAttribute CourseRequest courseDetailInformation) {
        Course course = courseService.addCourseDetailInformation(courseDetailInformation);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/update")
    public ResponseEntity<Course> update(@ModelAttribute CourseRequest courseDetailInformation) {
        Course course = courseService.updateCourseDetailInformation(courseDetailInformation);
        System.out.println(courseDetailInformation);
        if(course!=null){
            return ResponseEntity.ok(course);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCourse(@RequestParam("id") int id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourse(){
        List<Course> listCourse = courseService.getAllCourse();
        return ResponseEntity.ok(listCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") int id){
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }



}
