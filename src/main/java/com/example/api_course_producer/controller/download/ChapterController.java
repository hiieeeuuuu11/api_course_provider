package com.example.api_course_producer.controller.download;

import com.example.api_course_producer.model.course.Chapter;
import com.example.api_course_producer.service.download.ChapterDownloadService;
import com.example.api_course_producer.service.download.IChapterDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/download/chapter")
@CrossOrigin("*")
public class ChapterController {
    @Autowired
    ChapterDownloadService iChapterDownloadService;



    @GetMapping("/getallchapter")
    public ResponseEntity<List<Chapter>> getallChapter(){
        List<Chapter> chapterList = iChapterDownloadService.getallChapter();
        return ResponseEntity.ok(chapterList);
    }
    @GetMapping("/getchapterbycourse")
    public ResponseEntity<List<Chapter>> getChapterbyCourse(@RequestParam("courseid") int course_id){
        List<Chapter> chapterList=iChapterDownloadService.getChapterbyCourse(course_id);
        return  ResponseEntity.ok(chapterList);
    }

    @GetMapping("/getchapterbyid")
    public ResponseEntity<Chapter> getChapterbyId(@RequestParam("id") int id){
        Chapter chapter=iChapterDownloadService.getChapterbyId(id);
        return  ResponseEntity.ok(chapter);
    }

}

