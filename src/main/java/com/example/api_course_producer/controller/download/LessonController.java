package com.example.api_course_producer.controller.download;

import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.model.course.Lesson;
import com.example.api_course_producer.service.download.ILessonDownloadService;
import com.example.api_course_producer.service.download.LessonDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/download/lesson")
public class LessonController {
    @Autowired
    LessonDownloadService iLessonDownloadService;

    @GetMapping("/getalllesson")
    public ResponseEntity<List<Lesson>> getallLesson(){
        List<Lesson> lessonList =iLessonDownloadService.getallLesson();
        return ResponseEntity.ok(lessonList);
    }

    @GetMapping("/getlessonbychapter")
    public ResponseEntity<List<Lesson>> getLessonbyChapter(@RequestParam("chapter-id") int chapter_id){
        List<Lesson> lessonList =iLessonDownloadService.getLessonbyChapter(chapter_id);
        return ResponseEntity.ok(lessonList);
    }

    @GetMapping("/getlessonbyid")
    public ResponseEntity<Lesson> getLessonbyId(@RequestParam("id") int id){
        Lesson lesson =iLessonDownloadService.getLessonbyId(id);
        return ResponseEntity.ok(lesson);
    }

}
