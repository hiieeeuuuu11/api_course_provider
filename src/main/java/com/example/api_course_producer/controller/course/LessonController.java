package com.example.api_course_producer.controller.course;

import com.example.api_course_producer.dto.LessonRequest;
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
@RequestMapping("/upload/lesson")
@CrossOrigin("*")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/getpreurl")
    public ResponseEntity<Map<String,String>> getUrl(@RequestParam("type") String type) {
        Map<String,String> response = lessonService.getPresignUrlFromS3(type);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addlessontochapter")
    public ResponseEntity<Lesson> addLessonToChapter(@ModelAttribute LessonRequest lessonRequest) {
        Lesson response = lessonService.addLessonToChapter(lessonRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Lesson> updateChapter(@RequestBody Lesson lesson) {
        Lesson lesson1 = lessonService.updateLesson(lesson);
        if (lesson1 != null) {
            return new ResponseEntity<>(lesson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("id") int id) {
        lessonService.deleteLesson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getalllesson")
    public ResponseEntity<List<Lesson>> getallLesson(){
        List<Lesson> lessonList =lessonService.getallLesson();
        return ResponseEntity.ok(lessonList);
    }

    @GetMapping("/getlessonbychapter")
    public ResponseEntity<List<Lesson>> getLessonbyChapter(@RequestParam("chapter-id") int chapter_id){
        List<Lesson> lessonList =lessonService.getLessonbyChapter(chapter_id);
        return ResponseEntity.ok(lessonList);
    }

    @GetMapping("/getlessonbyid")
    public ResponseEntity<Lesson> getLessonbyId(@RequestParam("id") int id){
        Lesson lesson =lessonService.getLessonbyId(id);
        return ResponseEntity.ok(lesson);
    }



}
