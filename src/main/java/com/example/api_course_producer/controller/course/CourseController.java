package com.example.api_course_producer.controller.course;

import com.example.api_course_producer.dto.CourseRequest;
import com.example.api_course_producer.dto.response.BaseResponse;
import com.example.api_course_producer.dto.response.ResponseFactory;
import com.example.api_course_producer.entity.course.Course;
import com.example.api_course_producer.service.course.CourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;

  @PostMapping("/add")
  public ResponseEntity<BaseResponse<Course>> add(
      @ModelAttribute CourseRequest courseDetailInformation) {
    Course course = courseService.addCourseDetailInformation(courseDetailInformation);
    return ResponseFactory.success(course);
  }

  @PutMapping("/update")
  public ResponseEntity<BaseResponse<Course>> update(
      @ModelAttribute CourseRequest courseDetailInformation) {
    return ResponseFactory.success(
        courseService.updateCourseDetailInformation(courseDetailInformation));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<BaseResponse<Void>> deleteCourse(@RequestParam("id") int id) {
    courseService.delete(id);
    return ResponseFactory.success();
  }

  @GetMapping()
  public ResponseEntity<BaseResponse<List<Course>>> getAllCourse() {
    return ResponseFactory.success(courseService.getAllCourse());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponse<Course>> getCourseById(@PathVariable("id") int id) {
    return ResponseFactory.success(courseService.getCourseById(id));
  }
}
