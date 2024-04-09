package com.example.api_course_producer.controller.upload;

import com.example.api_course_producer.dto.LessonRequest;
import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.service.upload.LessonUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/upload/lesson")
@CrossOrigin("*")
public class LessonUploadController {

    @Autowired
    LessonUploadService lessonUploadService;

    @GetMapping("/getpreurl")
    public ResponseEntity<Map<String,String>> getUrl(@RequestParam("type") String type) {
        Map<String,String> response = lessonUploadService.getPresignUrlFromS3(type);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addlessontochapter")
    public ResponseEntity<Lesson> addLessonToChapter(@ModelAttribute LessonRequest lessonRequest) {
        Lesson response = lessonUploadService.addLessonToChapter(lessonRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Lesson> updateChapter(@RequestBody Lesson lesson) {
        Lesson lesson1 = lessonUploadService.updateLesson(lesson);
        if (lesson1 != null) {
            return new ResponseEntity<>(lesson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteLesson(@RequestParam("id") int id) {
        lessonUploadService.deleteLesson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
