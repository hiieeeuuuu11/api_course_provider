package com.example.api_course_producer.controller.course;

import com.example.api_course_producer.dto.LessonRequest;
import com.example.api_course_producer.dto.response.BaseResponse;
import com.example.api_course_producer.dto.response.ResponseFactory;
import com.example.api_course_producer.entity.course.Lesson;
import com.example.api_course_producer.service.course.LessonService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lesson")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LessonController {

  private final LessonService lessonService;

  @GetMapping("/getpreurl")
  public ResponseEntity<BaseResponse<Map<String, String>>> getUrl(
      @RequestParam("type") String type) {
    return ResponseFactory.success(lessonService.getPresignUrlFromS3(type));
  }

  @PostMapping("/addlessontochapter")
  public ResponseEntity<BaseResponse<Lesson>> addLessonToChapter(
      @RequestBody LessonRequest lessonRequest) {
    Lesson response = lessonService.addLessonToChapter(lessonRequest);
    return ResponseFactory.success(response);
  }

  @PostMapping("/update")
  public ResponseEntity<BaseResponse<Lesson>> updateChapter(@RequestBody Lesson lesson) {
    return ResponseFactory.success(lessonService.updateLesson(lesson));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse<Void>> deleteLesson(@PathVariable("id") int id) {
    lessonService.deleteLesson(id);
    return ResponseFactory.success();
  }

  @GetMapping("/getalllesson")
  public ResponseEntity<BaseResponse<List<Lesson>>> getallLesson() {
    return ResponseFactory.success(lessonService.getallLesson());
  }

  @GetMapping("/getlessonbychapter/{chapter_id}")
  public ResponseEntity<BaseResponse<List<Lesson>>> getLessonbyChapter(
      @PathVariable("chapter_id") int chapter_id) {
    return ResponseFactory.success(lessonService.getLessonbyChapter(chapter_id));
  }

  @GetMapping("/getlessonbyid")
  public ResponseEntity<BaseResponse<Lesson>> getLessonbyId(@RequestParam("id") int id) {
    return ResponseFactory.success(lessonService.getLessonbyId(id));
  }
}
