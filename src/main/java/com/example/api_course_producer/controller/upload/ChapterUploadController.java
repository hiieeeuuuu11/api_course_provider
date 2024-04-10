package com.example.api_course_producer.controller.upload;

import com.example.api_course_producer.dto.ChapterRequest;
import com.example.api_course_producer.dto.ChapterResponse;
import com.example.api_course_producer.model.course.Author;
import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.service.upload.ChapterUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload/chapter")
@CrossOrigin("*")
public class ChapterUploadController {

    @Autowired
    ChapterUploadService chapterUploadService;

    @PostMapping("/addtocourse")
    public ResponseEntity<Chapter> add(@ModelAttribute ChapterRequest chapterDetailInfomation) {
        Chapter chapter = chapterUploadService.addChapterToCourse(chapterDetailInfomation);
        return ResponseEntity.ok(chapter);
    }

    @PostMapping("/update")
    public ResponseEntity<ChapterResponse> updateChapter(@RequestBody Chapter chapter) {
        Chapter chapter1 = chapterUploadService.updateChapter(chapter);
        ChapterResponse chapterResponse = new ChapterResponse(chapter1,chapter1.getCourse_id().getId());
        if (chapter1 != null) {
            return new ResponseEntity<>(chapterResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAuthor(@RequestParam("id") int id) {
        chapterUploadService.deleteChapter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
